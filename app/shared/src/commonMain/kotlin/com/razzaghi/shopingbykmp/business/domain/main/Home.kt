package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.HomeDTO
import com.razzaghi.shopingbykmp.business.domain.main.Address

data class Home(
    val address: Address = Address(),
    val banners: List<Banner> = listOf(),
    val categories: List<Category> = listOf(),
    val flashSale: FlashSale = FlashSale(),
    val mostSale: List<Product> = listOf(),
    val newestProduct: List<Product> = listOf()
)

fun HomeDTO.toHome() = Home(
    address = address?.toAddress() ?: Address(),
    banners = banners?.map { it.toBanner() }?: listOf(),
    categories = categories?.map { it.toCategory() }?: listOf(),
    flashSale = flashSale?.toFlashSale()?:FlashSale(),
    mostSale = mostSale?.map { it.toProduct() }?: listOf(),
    newestProduct = newestProduct?.map { it.toProduct() }?: listOf(),
)
