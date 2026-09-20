package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object NotificationsTable : Table("notifications") {
    val id = long("id").autoIncrement()
    val userId = integer("user_id").references(UsersTable.id)
    val title = varchar("title", 255)
    val description = text("description")
    val isRead = integer("is_read").default(0)

    override val primaryKey = PrimaryKey(id)
}