package business.domain.main


data class FlashSale(
    val expiredAt: String = "",
    val products: List<Product> = listOf()
)