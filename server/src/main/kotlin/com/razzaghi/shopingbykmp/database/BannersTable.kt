package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object BannersTable : Table("banners") {
    val id = long("id").autoIncrement()
    val imageUrl = varchar("image_url", 255)
    override val primaryKey = PrimaryKey(id)
}