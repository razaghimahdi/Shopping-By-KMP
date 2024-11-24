package business.datasource.network.main.responses

import business.domain.main.Category
import business.domain.main.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProductDTO(
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Long?,
    @SerialName("image") val image: String?,
    @SerialName("isLike") val isLike: Boolean?,
    @SerialName("likes") val likes: Int?,
    @SerialName("price") val price: Long?,
    @SerialName("rate") val rate: Double?,
    @SerialName("title") val title: String?,
    @SerialName("category") val category: CategoryDTO?,
    @SerialName("comments") val comments: List<CommentDTO>?,
    @SerialName("gallery") val gallery: List<String>?,
)

fun ProductDTO.toProduct() = Product(
    description = description ?: "",
    id = id ?: 0,
    image = image ?: "",
    isLike = isLike ?: false,
    likes = likes ?: 0,
    price = price ?: 0L,
    rate = rate ?: 0.0,
    title = title ?: "",
    category = category?.toCategory() ?: Category(),
    comments = comments?.map { it.toComment() } ?: listOf(),
    gallery = gallery ?: listOf()
)