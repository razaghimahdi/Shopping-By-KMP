package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    @SerialName("icon") val icon: String?,
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("parent") val parent: Int?,
)