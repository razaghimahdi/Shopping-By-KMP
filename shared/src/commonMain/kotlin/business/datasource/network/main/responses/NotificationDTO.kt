package business.datasource.network.main.responses

import business.domain.main.Address
import business.domain.main.Notification
import business.domain.main.Order
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import presentation.ui.main.checkout.view_model.shippingType_global


@Serializable
data class NotificationDTO(
    @SerialName("id") val id: Long?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("is_read") val isRead: Int?,
)


fun NotificationDTO.toNotification() = Notification(
    id = id ?: 0L,
    title = title ?: "",
    description = description ?: "",
    isRead = isRead == 0,
    createAt = "1h"
)