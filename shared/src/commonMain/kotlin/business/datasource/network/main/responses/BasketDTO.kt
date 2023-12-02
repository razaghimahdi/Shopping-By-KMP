package business.datasource.network.main.responses

import business.domain.main.Basket
import business.domain.main.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketDTO(
    @SerialName("count") val count: Int?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("id") val id: Int?,
    @SerialName("product") val product: ProductDTO?,
    @SerialName("product_id") val productId: Int?,
    @SerialName("status") val status: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("user_id") val userId: Int?
)

fun BasketDTO.toBasket() = Basket(
    id = id ?: 0,
    count = count ?: 0,
    createdAt = createdAt ?: "",
    product = product?.toProduct() ?: Product(),
    productId = productId ?: 0,
    status = status ?: "",
    updatedAt = updatedAt ?: "",
    userId = userId ?: 0
)