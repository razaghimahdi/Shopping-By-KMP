package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketAddRequestDTO(
    @SerialName("product") val product: Long,
    @SerialName("count") val count: Int
)