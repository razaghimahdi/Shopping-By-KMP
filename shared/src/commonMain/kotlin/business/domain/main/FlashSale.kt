package business.domain.main


data class FlashSale(
    val expired_at: String = "",
    val products: List<Product> = listOf()
)