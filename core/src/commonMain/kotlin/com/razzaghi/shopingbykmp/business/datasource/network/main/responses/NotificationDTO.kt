package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDTO(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("is_read") val isRead: Int?, // 0 for unread, 1 for read
)