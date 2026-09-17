package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.NotificationDTO

data class Notification(
    val id: Long,
    val title: String,
    val description: String,
    val createAt: String,
    val isRead: Boolean,
)



fun NotificationDTO.toNotification() = Notification(
    id = id ?: 0L,
    title = title ?: "",
    description = description ?: "",
    isRead = isRead == 0,
    createAt = "1h"
)