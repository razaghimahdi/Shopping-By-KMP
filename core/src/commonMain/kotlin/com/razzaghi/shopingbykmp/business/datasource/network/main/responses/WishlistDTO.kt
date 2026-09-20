package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WishlistDTO(
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("products") val products: List<ProductDTO>?
)