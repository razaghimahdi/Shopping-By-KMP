package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BasketDTO
import com.razzaghi.shopingbykmp.business.domain.main.Category
import com.razzaghi.shopingbykmp.presentation.util.Format


data class Basket(
    val id: Long,
    val count: Int,
    val product: Product
) {
    fun getPrice() = "$ ${Format(product.price.toInt())}"
}


fun BasketDTO.toBasket() = Basket(
    id = id ?: 0,
    count = count ?: 0,
    product = product?.toProduct() ?: Product()
)