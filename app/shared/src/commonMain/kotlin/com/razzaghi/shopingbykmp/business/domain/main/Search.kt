package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.SearchDTO


data class Search(
    val products: List<Product> = listOf(),
)



fun SearchDTO.toSearch() = Search(
    products = products?.map { it.toProduct() } ?: listOf(),
)

