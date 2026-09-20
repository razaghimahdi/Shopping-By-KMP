package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.*
import com.razzaghi.shopingbykmp.database.CategoriesTable
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.ExpandedProductsTable
import org.jetbrains.exposed.sql.*

interface SearchRepository {
    suspend fun getSearchFilter(): SearchFilterDTO
    suspend fun searchProducts(
        minPrice: Int?,
        maxPrice: Int?,
        sort: Int?,
        categoriesId: String?,
        page: Int
    ): SearchDTO
}

class SearchRepositoryImpl : SearchRepository {

    override suspend fun getSearchFilter(): SearchFilterDTO = dbQuery {
        // Fetch all categories using named arguments
        val categories = CategoriesTable.selectAll().map {
            CategoryDTO(
                icon = it[CategoriesTable.icon],
                id = it[CategoriesTable.id],
                name = it[CategoriesTable.name],
                parent = it[CategoriesTable.parent]
            )
        }

        // Aggregate query to find the absolute minimum and maximum prices in the database
        val minPriceExp = ExpandedProductsTable.price.min()
        val maxPriceExp = ExpandedProductsTable.price.max()

        val priceRow = ExpandedProductsTable.slice(minPriceExp, maxPriceExp).selectAll().singleOrNull()
        val minPrice = priceRow?.get(minPriceExp)?.toInt() ?: 0
        val maxPrice = priceRow?.get(maxPriceExp)?.toInt() ?: 10000

        SearchFilterDTO(
            categories = categories,
            minPrice = minPrice,
            maxPrice = maxPrice
        )
    }

    override suspend fun searchProducts(
        minPrice: Int?,
        maxPrice: Int?,
        sort: Int?,
        categoriesId: String?,
        page: Int
    ): SearchDTO = dbQuery {
        val query = ExpandedProductsTable.selectAll()

        // Apply Price Filters
        if (minPrice != null) query.andWhere { ExpandedProductsTable.price greaterEq minPrice.toLong() }
        if (maxPrice != null) query.andWhere { ExpandedProductsTable.price lessEq maxPrice.toLong() }

        // Apply Category Filters (Assuming categoriesId is a comma-separated string like "1,2,5")
        if (!categoriesId.isNullOrBlank()) {
            val categoryIdsList = categoriesId.split(",").mapNotNull { it.toLongOrNull() }
            if (categoryIdsList.isNotEmpty()) {
                query.andWhere { ExpandedProductsTable.categoryId inList categoryIdsList }
            }
        }

        // Apply Sorting (Assuming 1 = Price Ascending, 2 = Price Descending for this example)
        when (sort) {
            1 -> query.orderBy(ExpandedProductsTable.price to SortOrder.ASC)
            2 -> query.orderBy(ExpandedProductsTable.price to SortOrder.DESC)
            else -> query.orderBy(ExpandedProductsTable.id to SortOrder.DESC) // Default sort
        }

        // Apply Pagination
        val pageSize = 20
        val offset = ((page.takeIf { it > 0 } ?: 1) - 1) * pageSize
        query.limit(pageSize, offset = offset.toLong())

        // Map results to DTOs
        val products = query.map { row ->
            ProductDTO(
                id = row[ExpandedProductsTable.id],
                title = row[ExpandedProductsTable.title],
                price = row[ExpandedProductsTable.price],
                description = row[ExpandedProductsTable.description],
                image = row[ExpandedProductsTable.image],
                likes = row[ExpandedProductsTable.likes],
                rate = row[ExpandedProductsTable.rate],
                isLike = false,
                category = null, comments = emptyList(), gallery = emptyList()
            )
        }

        SearchDTO(products = products)
    }
}