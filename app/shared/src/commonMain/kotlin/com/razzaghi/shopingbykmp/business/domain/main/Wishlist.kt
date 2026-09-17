package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.WishlistDTO


data class Wishlist(
    val categories: List<Category> = listOf(),
    val products: List<Product> = listOf(),
)


fun WishlistDTO.toWishlist() = Wishlist(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    products = products?.map { it.toProduct() } ?: listOf(),
)

