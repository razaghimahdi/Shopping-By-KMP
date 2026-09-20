package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDTO(
    @SerialName("name") val name: String?,
    @SerialName("email") val email: String?,
    @SerialName("age") val age: String?,
    @SerialName("image") val image: String?
)