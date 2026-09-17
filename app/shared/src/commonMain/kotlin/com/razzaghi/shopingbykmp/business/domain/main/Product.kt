package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProductDTO
import com.razzaghi.shopingbykmp.presentation.util.Format

data class Product(
    val description: String = "",
    val id: Long = 0,
    val image: String = "",
    val isLike: Boolean = false,
    val likes: Int = 0,
    val price: Long = 0L,
    val rate: Double = 0.0,
    val title: String = "",
    val category: Category = Category(),
    val comments: List<Comment> = listOf(),
    val gallery: List<String> = listOf(),
) {
    fun getPrice() = "$ ${Format(price.toInt())}"
}

val product_sample = Product(
    description = "Such a great Shoes",
    image = "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/17c9cb39-4a80-4c27-8ff1-57028b5f91d6/air-force-1-high-womens-shoes-vBhHD4.png",
    isLike = true,
    price = 30,
    rate = 4.4,
    title = "Nike-121",
    category = Category(name = "Shoes")
)

fun ProductDTO.toProduct() = Product(
    description = description ?: "",
    id = id ?: 0,
    image = image ?: "",
    isLike = isLike ?: false,
    likes = likes ?: 0,
    price = price ?: 0L,
    rate = rate ?: 0.0,
    title = title ?: "",
    category = category?.toCategory() ?: Category(),
    comments = comments?.map { it.toComment() } ?: listOf(),
    gallery = gallery ?: listOf()
)