package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.*
import com.razzaghi.shopingbykmp.database.*
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface BasketRepository {
    suspend fun getBasket(email: String): List<BasketDTO>
    suspend fun addToBasket(email: String, request: BasketAddRequestDTO): Boolean
    suspend fun deleteFromBasket(email: String, request: BasketDeleteRequestDTO): Boolean
}

class BasketRepositoryImpl : BasketRepository {

    override suspend fun getBasket(email: String): List<BasketDTO> = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery emptyList()

        // Join BasketsTable with ProductsTable to return complete ProductDTOs
        BasketsTable.join(ProductsTable, JoinType.INNER, additionalConstraint = { BasketsTable.productId eq ProductsTable.id })
            .selectAll().where { BasketsTable.userId eq userId }
            .map { row ->
                BasketDTO(
                    id = row[BasketsTable.id],
                    count = row[BasketsTable.count],
                    product = ProductDTO(
                        id = row[ProductsTable.id],
                        title = row[ProductsTable.title],
                        price = row[ProductsTable.price],
                        description = row[ProductsTable.description],
                        image = row[ProductsTable.image],
                        isLike = false, likes = 0, rate = 0.0, category = null, comments = emptyList(), gallery = emptyList()
                    )
                )
            }
    }

    override suspend fun addToBasket(email: String, request: BasketAddRequestDTO): Boolean = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery false

        val existingBasketItem = BasketsTable.selectAll()
            .where { (BasketsTable.userId eq userId) and (BasketsTable.productId eq request.product) }
            .singleOrNull()

        if (existingBasketItem != null) {
            // Product is already in the basket, update the count
            BasketsTable.update({ BasketsTable.id eq existingBasketItem[BasketsTable.id] }) {
                it[count] = existingBasketItem[BasketsTable.count] + request.count
            }
        } else {
            // New product, insert it
            BasketsTable.insert {
                it[this.userId] = userId
                it[this.productId] = request.product
                it[this.count] = request.count
            }
        }
        true
    }

    override suspend fun deleteFromBasket(email: String, request: BasketDeleteRequestDTO): Boolean = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery false

        val deletedRows = BasketsTable.deleteWhere {
            (BasketsTable.userId eq userId) and (BasketsTable.productId eq request.product)
        }
        deletedRows > 0
    }
}