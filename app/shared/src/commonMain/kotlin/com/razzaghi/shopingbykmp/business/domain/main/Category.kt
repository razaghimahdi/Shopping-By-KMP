package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CategoryDTO

data class Category(
    val icon: String = "",
    val id: Long = 0L,
    val name: String = "",
    val parent: Int = 0
)

val category_all = Category(
    icon = "",
    id = -1,
    name = "All",
    parent = 0,
)

fun CategoryDTO.toCategory() = Category(
    icon = icon ?: "",
    name = name ?: "",
    id = id ?: 0,
    parent = parent ?: 0,
)