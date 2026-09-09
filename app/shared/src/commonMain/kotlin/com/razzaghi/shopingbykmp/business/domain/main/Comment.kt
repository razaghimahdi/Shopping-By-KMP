package business.domain.main


data class Comment(
    val id: Long,
    val comment: String,
    val createAt: String,
    val rate: Double,
    val user: User
)