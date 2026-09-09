package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Long?,
    @SerialName("image") val image: String?,
    @SerialName("isLike") val isLike: Boolean?,
    @SerialName("likes") val likes: Int?,
    @SerialName("price") val price: Long?,
    @SerialName("rate") val rate: Double?,
    @SerialName("title") val title: String?,
    @SerialName("category") val category: CategoryDTO?,
    @SerialName("comments") val comments: List<CommentDTO>?,
    @SerialName("gallery") val gallery: List<String>?,
)