package business.domain.main

data class SearchFilter(
    val categories: List<Category> = listOf(),
    val minPrice: Int = 0,
    val maxPrice: Int = 10,
)
