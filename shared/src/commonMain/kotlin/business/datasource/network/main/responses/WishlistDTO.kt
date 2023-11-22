package business.datasource.network.main.responses

import business.domain.main.Wishlist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WishlistDTO(
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("products") val products: List<ProductDTO>?,
)

fun WishlistDTO.toWishlist() = Wishlist(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    products = products?.map { it.toProduct() } ?: listOf(),
)

