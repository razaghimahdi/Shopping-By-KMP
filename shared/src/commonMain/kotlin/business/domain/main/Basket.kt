package business.domain.main

import common.Format


data class Basket(
    val id: Long,
    val productId: Long,
    val category: Category,
    val title: String,
    val description: String,
    val image: String,
    val price: Long,
    val count: Int,
){
    fun getPrice() = "$ ${Format(price.toInt())}"
}