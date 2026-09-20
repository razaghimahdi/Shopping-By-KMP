package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.*
import com.razzaghi.shopingbykmp.database.AddressesTable
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.OrdersTable
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.JoinType

interface OrderRepository {
    suspend fun getOrdersForUser(email: String): List<OrderDTO>
}

class OrderRepositoryImpl : OrderRepository {
    override suspend fun getOrdersForUser(email: String): List<OrderDTO> = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery emptyList()

        OrdersTable.join(AddressesTable, JoinType.LEFT, additionalConstraint = { OrdersTable.addressId eq AddressesTable.id })
            .selectAll().where { OrdersTable.userId eq userId }
            .map { row ->

                val addressDTO = if (row.getOrNull(AddressesTable.id) != null) {
                    AddressDTO(
                        id = row[AddressesTable.id],
                        address = row[AddressesTable.address],
                        country = row[AddressesTable.country],
                        city = row[AddressesTable.city],
                        state = row[AddressesTable.state],
                        zipCode = row[AddressesTable.zipCode]
                    )
                } else null

                val productsList = emptyList<ProductDTO>()

                OrderDTO(
                    code = row[OrdersTable.code],
                    createdAt = row[OrdersTable.createdAt],
                    shippingType = row[OrdersTable.shippingType],
                    status = row[OrdersTable.status],
                    address = addressDTO,
                    products = productsList
                )
            }
    }
}