package business.domain.main

data class Notification(
    val title: String,
    val desc: String,
    val createAt: String,
    val isRead: Boolean,
)
