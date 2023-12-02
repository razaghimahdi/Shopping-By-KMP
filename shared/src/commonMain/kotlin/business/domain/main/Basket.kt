package business.domain.main

data class Basket(
    val count: Int = 0,
    val createdAt: String = "",
    val id: Int = 0,
    val product: Product = Product(),
    val productId: Int = 0,
    val status: String = "",
    val updatedAt: String = "",
    val userId: Int = 0,
)