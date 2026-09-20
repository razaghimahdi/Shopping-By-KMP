package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchFilterDTO(
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("min_price") val minPrice: Int?,
    @SerialName("max_price") val maxPrice: Int?,
)