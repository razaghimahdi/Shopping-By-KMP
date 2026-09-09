package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlashSaleDTO(
    @SerialName("expired_at") val expiredAt: String? = null,
    @SerialName("products") val products: List<ProductDTO>? = listOf()
)