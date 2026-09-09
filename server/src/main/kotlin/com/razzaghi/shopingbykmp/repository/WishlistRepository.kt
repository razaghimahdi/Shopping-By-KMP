package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.*
import com.razzaghi.shopingbykmp.database.*
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface WishlistRepository {
    suspend fun getWishlist(email: String, categoryId: Long?, page: Int): WishlistDTO
    suspend fun toggleLike(email: String, productId: Long): Boolean
}

class WishlistRepositoryImpl : WishlistRepository {

    override suspend fun getWishlist(email: String, categoryId: Long?, page: Int): WishlistDTO = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery WishlistDTO(emptyList(), emptyList())

        val likedProductIds = ProductLikesTable.selectAll()
            .where { ProductLikesTable.userId eq userId }
            .map { it[ProductLikesTable.productId] }

        if (likedProductIds.isEmpty()) {
            return@dbQuery WishlistDTO(categories = emptyList(), products = emptyList())
        }

        val query = ExpandedProductsTable.selectAll()
            .where { ExpandedProductsTable.id inList likedProductIds }

        if (categoryId != null) {
            query.andWhere { ExpandedProductsTable.categoryId eq categoryId }
        }

        // Apply Pagination
        val pageSize = 10
        val offset = ((page.takeIf { it > 0 } ?: 1) - 1) * pageSize
        query.limit(pageSize, offset = offset.toLong())

        val products = query.map { row ->
            ProductDTO(
                id = row[ExpandedProductsTable.id],
                title = row[ExpandedProductsTable.title],
                price = row[ExpandedProductsTable.price],
                description = row[ExpandedProductsTable.description],
                image = row[ExpandedProductsTable.image],
                likes = row[ExpandedProductsTable.likes],
                rate = row[ExpandedProductsTable.rate],
                isLike = true, // Since it's in the wishlist, isLike is inherently true
                category = null, comments = emptyList(), gallery = emptyList()
            )
        }

        val categoryIds = ExpandedProductsTable.selectAll()
            .where { ExpandedProductsTable.id inList likedProductIds }
            .mapNotNull { it[ExpandedProductsTable.categoryId] }
            .distinct()

        val categories = CategoriesTable.selectAll()
            .where { CategoriesTable.id inList categoryIds }
            .map {
                CategoryDTO(
                    icon = it[CategoriesTable.icon],
                    id = it[CategoriesTable.id],
                    name = it[CategoriesTable.name],
                    parent = it[CategoriesTable.parent]
                )
            }

        WishlistDTO(
            categories = categories,
            products = products
        )
    }

    override suspend fun toggleLike(email: String, productId: Long): Boolean = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery false

        val existingLike = ProductLikesTable.selectAll()
            .where { (ProductLikesTable.userId eq userId) and (ProductLikesTable.productId eq productId) }
            .singleOrNull()

        if (existingLike != null) {
            // User already liked it, so clicking again removes it (Unlike)
            ProductLikesTable.deleteWhere { ProductLikesTable.id eq existingLike[ProductLikesTable.id] }
        } else {
            // User hasn't liked it, add to wishlist
            ProductLikesTable.insert {
                it[this.userId] = userId
                it[this.productId] = productId
            }
        }
        true
    }
}