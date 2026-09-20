package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object ProductLikesTable : Table("product_likes") {
    val id = long("id").autoIncrement()
    val userId = integer("user_id").references(UsersTable.id)
    val productId = long("product_id").references(ExpandedProductsTable.id)
    override val primaryKey = PrimaryKey(id)
}