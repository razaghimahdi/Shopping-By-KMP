package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object ExpandedProductsTable : Table("expanded_products") {
    val id = long("id").autoIncrement()
    val categoryId = long("category_id").references(CategoriesTable.id).nullable()
    val title = varchar("title", 255)
    val price = long("price")
    val description = text("description").nullable()
    val image = varchar("image", 255).nullable()
    val likes = integer("likes").default(0)
    val rate = double("rate").default(0.0)
    override val primaryKey = PrimaryKey(id)
}