package business.domain.main


data class User(
    val firstName: String = "",
    val image: String = "",
    val lastName: String = ""
) {
    fun fetchName() = "$firstName $lastName"
}