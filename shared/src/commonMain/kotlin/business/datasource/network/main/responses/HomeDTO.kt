package business.datasource.network.main.responses

import business.domain.main.Address
import business.domain.main.FlashSale
import business.domain.main.Home
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HomeDTO(
    @SerialName("address") val address: AddressDTO?,
    @SerialName("banners") val banners: List<BannerDTO>?,
    @SerialName("categories") val categories: List<CategoryDTO>?,
    @SerialName("flash_sale") val flashSale: FlashSaleDTO?,
    @SerialName("most_sale") val mostSale: List<ProductDTO>?,
    @SerialName("newest_product") val newestProduct: List<ProductDTO>?
)

fun HomeDTO.toHome() = Home(
    address = address?.toAddress() ?: Address(),
    banners = banners?.map { it.toBanner() }?: listOf(),
    categories = categories?.map { it.toCategory() }?: listOf(),
    flashSale = flashSale?.toFlashSale()?:FlashSale(),
    mostSale = mostSale?.map { it.toProduct() }?: listOf(),
    newestProduct = newestProduct?.map { it.toProduct() }?: listOf(),
)
