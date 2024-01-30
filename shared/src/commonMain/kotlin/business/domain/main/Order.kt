package business.domain.main

data class Order(
    val products: List<Product>,
    val status: Int,
    val code: String,
    val createAt: String,
    val totalCost: String,
    val address: Address,
    val shippingType: ShippingType,
)
