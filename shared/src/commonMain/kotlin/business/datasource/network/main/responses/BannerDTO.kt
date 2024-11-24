package business.datasource.network.main.responses

import business.domain.main.Banner
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BannerDTO(
    @SerialName("banner") val banner: String?,
    @SerialName("id") val id: Long?
)

fun BannerDTO.toBanner() = Banner(banner = banner ?: "", id = id ?: 0)