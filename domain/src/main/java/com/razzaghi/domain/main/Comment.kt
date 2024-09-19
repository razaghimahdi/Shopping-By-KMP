package business.domain.main


data class Comment(
    val id: Int,
    val comment: String,
    val createAt: String,
    val rate: Double,
    val user: User
)