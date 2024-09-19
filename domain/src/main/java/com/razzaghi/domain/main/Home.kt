package business.domain.main

data class Home(
    val address: Address = Address(),
    val banners: List<Banner> = listOf(),
    val categories: List<Category> = listOf(),
    val flashSale: FlashSale = FlashSale(),
    val mostSale: List<Product> = listOf(),
    val newestProduct: List<Product> = listOf()
)