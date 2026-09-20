package com.razzaghi.shopingbykmp.database

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val email = varchar("email", 128).uniqueIndex()
    val passwordHash = varchar("password_hash", 255)

    // Add these new columns for the profile:
    val age = varchar("age", 10).nullable()
    val image = varchar("image", 255).nullable()

    override val primaryKey = PrimaryKey(id)
}