package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object ProductGalleriesTable : Table("product_galleries") {
    val id = long("id").autoIncrement()
    val productId = long("product_id").references(ExpandedProductsTable.id)
    val imageUrl = varchar("image_url", 255)
    override val primaryKey = PrimaryKey(id)
}