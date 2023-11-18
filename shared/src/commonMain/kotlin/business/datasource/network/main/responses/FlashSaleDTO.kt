package business.datasource.network.main.responses

import business.domain.main.FlashSale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FlashSaleDTO(
    @SerialName("expired_at") val expired_at: String? = null,
    @SerialName("products") val products: List<ProductDTO>? = listOf()
)

fun FlashSaleDTO.toFlashSale() = FlashSale(
    expired_at = expired_at ?: "",
    products = products?.map { it.toProduct() } ?: listOf(),
)
