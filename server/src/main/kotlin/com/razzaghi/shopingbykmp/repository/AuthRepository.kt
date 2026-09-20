package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.LoginRequestDTO
import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.RegisterRequestDTO
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.mindrot.jbcrypt.BCrypt

interface AuthRepository {
    suspend fun registerUser(request: RegisterRequestDTO): Boolean
    suspend fun validateUser(request: LoginRequestDTO): Boolean
}

class AuthRepositoryImpl : AuthRepository {

    override suspend fun registerUser(request: RegisterRequestDTO): Boolean = dbQuery {
        // Check if the user already exists
        val existingUser = UsersTable.selectAll().where { UsersTable.email eq request.email }.singleOrNull()
        if (existingUser != null) return@dbQuery false

        // Hash the password securely
        val hashedPassword = BCrypt.hashpw(request.password, BCrypt.gensalt())

        // Insert into the database
        UsersTable.insert {
            it[name] = request.name
            it[email] = request.email
            it[passwordHash] = hashedPassword
        }
        true
    }

    override suspend fun validateUser(request: LoginRequestDTO): Boolean = dbQuery {
        // Find the user by email
        val user = UsersTable.selectAll().where { UsersTable.email eq request.email }.singleOrNull()

        if (user != null) {
            val hashed = user[UsersTable.passwordHash]
            // Check if the provided password matches the stored hash
            BCrypt.checkpw(request.password, hashed)
        } else {
            false
        }
    }
}