package com.razzaghi.shopingbykmp.business.datasource.network.main.responses

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