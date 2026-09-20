package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object OrdersTable : Table("orders") {
    val id = long("id").autoIncrement()
    val code = varchar("code", 50).uniqueIndex()
    val userId = integer("user_id").references(UsersTable.id)
    val addressId = long("address_id").references(AddressesTable.id)
    val createdAt = varchar("created_at", 50)
    val shippingType = integer("shipping_type")
    val status = integer("status")
    override val primaryKey = PrimaryKey(id)
}