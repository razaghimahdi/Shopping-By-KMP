package business.datasource.network.main.responses

import business.domain.main.Basket
import business.domain.main.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketDTO(
    @SerialName("id") val id: Int?,
    @SerialName("product_id") val productId: Int?,
    @SerialName("category") val category: CategoryDTO?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("image") val image: String?,
    @SerialName("price") val price: Int?,
    @SerialName("count") val count: Int?,
)

fun BasketDTO.toBasket() = Basket(
    id = id ?: 0,
    count = count ?: 0,
    productId = productId ?: 0,
    category = category?.toCategory() ?: Category(),
    title = title ?: "",
    description = description ?: "",
    image = image ?: "",
    price = price ?: 0,
)