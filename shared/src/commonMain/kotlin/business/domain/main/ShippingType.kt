package business.domain.main

data class ShippingType(val title: String, val price: Int, val arrivalDay: Int){
    fun getEstimatedDay() = "Estimated Arrival in $arrivalDay days"
    fun getPrice() = "$ $price"
}
