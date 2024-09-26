package business.domain.main

data class Notification(
    val id: Long,
    val title: String,
    val description: String,
    val createAt: String,
    val isRead: Boolean,
)
