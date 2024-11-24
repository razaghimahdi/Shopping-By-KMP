package business.datasource.network.main.responses

import business.domain.main.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CategoryDTO(
    @SerialName("icon") val icon: String?,
    @SerialName("id") val id: Long?,
    @SerialName("name") val name: String?,
    @SerialName("parent") val parent: Int?,
)

fun CategoryDTO.toCategory() = Category(
    icon = icon ?: "",
    name = name ?: "",
    id = id ?: 0,
    parent = parent ?: 0,
)