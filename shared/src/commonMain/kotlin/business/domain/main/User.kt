package business.domain.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class User(
    val firstName: String = "",
    val image: String = "",
    val lastName: String = ""
) {
    fun fetchName() = "$firstName $lastName"
}