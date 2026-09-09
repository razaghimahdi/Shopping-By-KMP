package business.domain.main

import com.razzaghi.shopingbykmp.business.domain.main.Address

data class Home(
    val address: Address = Address(),
    val banners: List<Banner> = listOf(),
    val categories: List<Category> = listOf(),
    val flashSale: FlashSale = FlashSale(),
    val mostSale: List<Product> = listOf(),
    val newestProduct: List<Product> = listOf()
)