package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDTO(
    @SerialName("id") val id: Long?,
    @SerialName("comment") val comment: String?,
    @SerialName("created_at") val createAt: String?,
    @SerialName("rate") val rate: Double?,
    @SerialName("user") val user: UserDTO?
)