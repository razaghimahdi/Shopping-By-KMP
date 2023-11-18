package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewestProductDTO(
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Int?,
    @SerialName("image") val image: String?,
    @SerialName("isLike") val isLike: Boolean?,
    @SerialName("likes") val likes: Int?,
    @SerialName("price") val price: Int?,
    @SerialName("rate") val rate: Double?,
    @SerialName("title") val title: String?
)

