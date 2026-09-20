package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object ProductsTable : Table("products") {
    val id = long("id").autoIncrement()
    val title = varchar("title", 255)
    val price = long("price")
    val description = text("description").nullable()
    val image = varchar("image", 255).nullable()
    override val primaryKey = PrimaryKey(id)
}