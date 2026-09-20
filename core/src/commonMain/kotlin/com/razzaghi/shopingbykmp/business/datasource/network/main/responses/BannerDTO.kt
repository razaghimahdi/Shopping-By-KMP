package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerDTO(
    @SerialName("id") val id: Long?,
    @SerialName("banner") val banner: String?
)