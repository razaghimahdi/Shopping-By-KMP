package business.datasource.network.main.responses

import business.domain.main.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO(
    @SerialName("first_name") val firstName: String?,
    @SerialName("image") val image: String?,
    @SerialName("last_name") val lastName: String?
)

fun UserDTO.toUser() = User(
    firstName = firstName ?: "",
    image = image ?: "",
    lastName = lastName ?: "",
)
