package business.domain.main

import business.datasource.network.main.responses.CommentDTO

data class Product(
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val isLike: Boolean = false,
    val likes: Int = 0,
    val price: Int = 0,
    val rate: Double = 0.0,
    val title: String = "",
    val category: Category = Category(),
    val comments: List<Comment> = listOf(),
    val gallery: List<String> = listOf(),
) {
    fun getPrice() = "$ $price"
}