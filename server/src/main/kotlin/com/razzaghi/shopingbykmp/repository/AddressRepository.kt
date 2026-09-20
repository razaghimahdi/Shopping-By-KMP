package com.razzaghi.shopingbykmp.repository

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressRequestDTO
import com.razzaghi.shopingbykmp.database.AddressesTable
import com.razzaghi.shopingbykmp.database.DatabaseFactory.dbQuery
import com.razzaghi.shopingbykmp.database.UsersTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

interface AddressRepository {
    suspend fun getAddresses(email: String): List<AddressDTO>
    suspend fun addAddress(email: String, request: AddressRequestDTO): Boolean
}

class AddressRepositoryImpl : AddressRepository {

    override suspend fun getAddresses(email: String): List<AddressDTO> = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery emptyList()

        AddressesTable.selectAll().where { AddressesTable.userId eq userId }
            .map {
                AddressDTO(
                    id = it[AddressesTable.id],
                    address = it[AddressesTable.address],
                    country = it[AddressesTable.country],
                    city = it[AddressesTable.city],
                    state = it[AddressesTable.state],
                    zipCode = it[AddressesTable.zipCode]
                )
            }
    }

    override suspend fun addAddress(email: String, request: AddressRequestDTO): Boolean = dbQuery {
        val userRow = UsersTable.selectAll().where { UsersTable.email eq email }.singleOrNull()
        val userId = userRow?.get(UsersTable.id) ?: return@dbQuery false

        AddressesTable.insert {
            it[this.userId] = userId
            it[address] = request.address
            it[country] = request.country
            it[city] = request.city
            it[state] = request.state
            it[zipCode] = request.zipCode
        }
        true
    }
}