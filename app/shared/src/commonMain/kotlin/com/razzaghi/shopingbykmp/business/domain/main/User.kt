package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.UserDTO


data class User(
    val firstName: String = "",
    val image: String = "",
    val lastName: String = ""
) {
    fun fetchName() = "$firstName $lastName"
}


fun UserDTO.toUser() = User(
    firstName = name ?: "",
    image = image ?: "",
    lastName = name ?: "",
)
