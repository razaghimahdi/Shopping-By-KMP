package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object BasketsTable : Table("baskets") {
    val id = long("id").autoIncrement()
    val userId = integer("user_id").references(UsersTable.id)
    val productId = long("product_id").references(ProductsTable.id)
    val count = integer("count")
    override val primaryKey = PrimaryKey(id)
}