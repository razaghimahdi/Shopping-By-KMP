package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.SearchFilterDTO

data class SearchFilter(
    val categories: List<Category> = listOf(),
    val minPrice: Int = 0,
    val maxPrice: Int = 10,
)

fun SearchFilterDTO.toSearchFilter() = SearchFilter(
    categories = categories?.map { it.toCategory() } ?: listOf(),
    minPrice = minPrice ?: 0,
    maxPrice = maxPrice ?: 0,
)

