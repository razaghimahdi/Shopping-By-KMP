package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.FlashSaleDTO


data class FlashSale(
    val expiredAt: String = "",
    val products: List<Product> = listOf()
)

fun FlashSaleDTO.toFlashSale() = FlashSale(
    expiredAt = expiredAt ?: "",
    products = products?.map { it.toProduct() } ?: listOf(),
)
