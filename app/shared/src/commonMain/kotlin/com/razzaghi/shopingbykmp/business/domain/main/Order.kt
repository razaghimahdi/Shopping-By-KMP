package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.OrderDTO
import com.razzaghi.shopingbykmp.business.domain.main.Address
import com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model.shippingType_global

data class Order(
    val products: List<Product>,
    val status: Int,
    val code: String,
    val createdAt: String,
    val address: Address,
    val shippingType: ShippingType,
) {
    fun getAmount() = "$ ${products.sumOf { it.price } + shippingType.price}"
}

fun OrderDTO.toOrder() = Order(
    code = code ?: "",
    createdAt = createdAt ?: "",
    shippingType = shippingType_global[shippingType?:0] ,
    status = status ?: 0,
    address = address?.toAddress() ?: Address(),
    products = products?.map { it.toProduct() } ?: listOf(),
)