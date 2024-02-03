package business.domain.main

data class Order(
    val id: Int,
    val products: List<Product>,
    val status: Int,
    val code: String,
    val createAt: String,
    val amount: String,
    val address: Address,
    val shippingType: ShippingType,
)
