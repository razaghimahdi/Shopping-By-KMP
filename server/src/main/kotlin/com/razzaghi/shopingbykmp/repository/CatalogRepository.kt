package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.*
import com.razzaghi.shopingbykmp.database.*
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.JoinType

interface CatalogRepository {
    suspend fun getHomeData(email: String): HomeDTO
    suspend fun getProductDetails(email: String, productId: Long): ProductDTO?
}

class CatalogRepositoryImpl : CatalogRepository {

    override suspend fun getHomeData(email: String): HomeDTO = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id)

        val addressDto = userId?.let { uid ->
            AddressesTable.selectAll().where { AddressesTable.userId eq uid }.firstOrNull()?.let {
                AddressDTO(
                    id = it[AddressesTable.id],
                    address = it[AddressesTable.address],
                    country = it[AddressesTable.country],
                    city = it[AddressesTable.city],
                    state = it[AddressesTable.state],
                    zipCode = it[AddressesTable.zipCode]
                )
            }
        }

        val banners = BannersTable.selectAll().map {
            BannerDTO(id = it[BannersTable.id], banner = it[BannersTable.imageUrl])
        }

        val categories = CategoriesTable.selectAll().map {
            CategoryDTO(
                icon = it[CategoriesTable.icon],
                id = it[CategoriesTable.id],
                name = it[CategoriesTable.name],
                parent = it[CategoriesTable.parent]
            )
        }

        val products = ExpandedProductsTable.selectAll().limit(10).map { row ->
            ProductDTO(
                id = row[ExpandedProductsTable.id],
                title = row[ExpandedProductsTable.title],
                price = row[ExpandedProductsTable.price],
                description = row[ExpandedProductsTable.description],
                image = row[ExpandedProductsTable.image],
                likes = row[ExpandedProductsTable.likes],
                rate = row[ExpandedProductsTable.rate],
                isLike = false, category = null, comments = emptyList(), gallery = emptyList()
            )
        }

        val flashSaleRow = FlashSalesTable.selectAll().firstOrNull()
        val flashSaleDto = flashSaleRow?.let {
            FlashSaleDTO(
                expiredAt = it[FlashSalesTable.expiredAt],
                products = products.take(3) // Mocking 3 products in the flash sale
            )
        }

        HomeDTO(
            address = addressDto,
            banners = banners,
            categories = categories,
            flashSale = flashSaleDto,
            mostSale = products,
            newestProduct = products.reversed()
        )
    }

    override suspend fun getProductDetails(email: String, productId: Long): ProductDTO? = dbQuery {
        val productRow = ExpandedProductsTable.selectAll().where { ExpandedProductsTable.id eq productId }.singleOrNull()
            ?: return@dbQuery null

        val categoryDto = productRow[ExpandedProductsTable.categoryId]?.let { catId ->
            CategoriesTable.selectAll().where { CategoriesTable.id eq catId }.singleOrNull()?.let {
                CategoryDTO(
                    icon = it[CategoriesTable.icon],
                    id = it[CategoriesTable.id],
                    name = it[CategoriesTable.name],
                    parent = it[CategoriesTable.parent]
                )
            }
        }

        val gallery = ProductGalleriesTable.selectAll().where { ProductGalleriesTable.productId eq productId }
            .map { it[ProductGalleriesTable.imageUrl] }

        val comments = CommentsTable.join(UsersTable, JoinType.INNER, additionalConstraint = { CommentsTable.userId eq UsersTable.id })
            .selectAll().where { CommentsTable.productId eq productId }
            .map { row ->
                CommentDTO(
                    id = row[CommentsTable.id],
                    comment = row[CommentsTable.comment],
                    createAt = row[CommentsTable.createdAt],
                    rate = row[CommentsTable.rate],
                    user = UserDTO(name = row[UsersTable.name], image = row[UsersTable.image])
                )
            }

        ProductDTO(
            id = productRow[ExpandedProductsTable.id],
            title = productRow[ExpandedProductsTable.title],
            price = productRow[ExpandedProductsTable.price],
            description = productRow[ExpandedProductsTable.description],
            image = productRow[ExpandedProductsTable.image],
            likes = productRow[ExpandedProductsTable.likes],
            rate = productRow[ExpandedProductsTable.rate],
            isLike = false,
            category = categoryDto,
            comments = comments,
            gallery = gallery
        )
    }
}