package business.datasource.network.main.responses

import business.domain.main.SearchFilter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchFilterDTO(
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("min_price") val minPrice: Int?,
    @SerialName("max_price") val maxPrice: Int?,
)

fun SearchFilterDTO.toSearchFilter() = SearchFilter(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    minPrice = minPrice ?: 0,
    maxPrice = maxPrice ?: 0,
)

