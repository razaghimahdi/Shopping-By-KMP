package business.domain.main


data class Wishlist(
    val categories: List<Category> = listOf(),
    val products: List<Product> = listOf(),
)
