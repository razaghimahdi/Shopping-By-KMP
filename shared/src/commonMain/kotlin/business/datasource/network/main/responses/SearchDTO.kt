package business.datasource.network.main.responses

import business.domain.main.Search
import business.domain.main.Wishlist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchDTO(
    @SerialName("products") val products: List<ProductDTO>?,
)

fun SearchDTO.toSearch() = Search(
    products = products?.map { it.toProduct() } ?: listOf(),
)

