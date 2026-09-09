package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object CommentsTable : Table("comments") {
    val id = long("id").autoIncrement()
    val productId = long("product_id").references(ExpandedProductsTable.id)
    val userId = integer("user_id").references(UsersTable.id)
    val comment = text("comment")
    val rate = double("rate")
    val createdAt = varchar("created_at", 50)
    override val primaryKey = PrimaryKey(id)
}