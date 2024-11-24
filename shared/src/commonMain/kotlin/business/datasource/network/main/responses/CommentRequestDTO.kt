package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CommentRequestDTO(
    @SerialName("comment") val comment: String,
    @SerialName("rate") val rate: Double,
    @SerialName("product_id") val productId: Long,
)