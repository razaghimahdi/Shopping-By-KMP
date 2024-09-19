package business.domain.main

 
data class Basket(
    val id: Int,
    val productId: Int,
    val category: Category,
    val title: String,
    val description: String,
    val image: String,
    val price: Int,
    val count: Int,
){
    fun getPrice() = "$ $price"
}