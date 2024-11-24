package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BasketAddRequestDTO(
    @SerialName("count") val count: Int,
    @SerialName("product") val product: Long,
)
