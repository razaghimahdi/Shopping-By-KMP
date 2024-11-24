package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BasketDeleteRequestDTO(
    @SerialName("product") val product: Long,
)
