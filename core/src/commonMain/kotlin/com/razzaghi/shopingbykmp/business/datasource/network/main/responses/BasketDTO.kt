package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketDTO(
    @SerialName("id") val id: Long?,
    @SerialName("count") val count: Int?,
    @SerialName("product") val product: ProductDTO?
)