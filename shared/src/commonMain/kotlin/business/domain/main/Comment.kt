package business.domain.main


data class Comment(
    val comment: String,
    val createAt: String,
    val rate: Double,
    val user: User
)