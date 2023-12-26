package business.datasource.network.main.responses


import business.domain.main.FlashSale
import business.domain.main.Home
import business.domain.main.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProfileDTO(
    @SerialName("name") val name: String?,
    @SerialName("profile_url") val profileUrl: String?,
)

fun ProfileDTO.toProfile() = Profile(name = name ?: "", profileUrl = profileUrl ?: "")
