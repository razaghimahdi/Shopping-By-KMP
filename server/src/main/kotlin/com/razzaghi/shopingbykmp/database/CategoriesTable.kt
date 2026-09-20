package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object CategoriesTable : Table("categories") {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val icon = varchar("icon", 255).nullable()
    val parent = integer("parent").default(0)
    override val primaryKey = PrimaryKey(id)
}