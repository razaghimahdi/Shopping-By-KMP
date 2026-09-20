package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProfileDTO
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

interface ProfileRepository {
    suspend fun getProfile(email: String): ProfileDTO?
    suspend fun updateProfile(email: String, name: String, age: String, imageUrl: String?): Boolean
}

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun getProfile(email: String): ProfileDTO? = dbQuery {
        UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()?.let {
            ProfileDTO(
                name = it[UsersTable.name],
                email = it[UsersTable.email],
                age = it[UsersTable.age],
                image = it[UsersTable.image]
            )
        }
    }

    override suspend fun updateProfile(email: String, name: String, age: String, imageUrl: String?): Boolean = dbQuery {
        val updatedRows = UsersTable.update({ UsersTable.email eq email }) {
            it[this.name] = name
            it[this.age] = age
            if (imageUrl != null) {
                it[this.image] = imageUrl
            }
        }
        updatedRows > 0
    }
}