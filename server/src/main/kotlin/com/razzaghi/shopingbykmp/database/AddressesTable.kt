package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object AddressesTable : Table("addresses") {
    val id = long("id").autoIncrement()
    val userId = integer("user_id").references(UsersTable.id)
    val address = varchar("address", 255)
    val country = varchar("country", 100)
    val city = varchar("city", 100)
    val state = varchar("state", 100)
    val zipCode = varchar("zip_code", 20)
    override val primaryKey = PrimaryKey(id)
}