package business.domain.main

import common.Format

data class ShippingType(val title: String, val price: Long, val arrivalDay: Int){
    fun getEstimatedDay() = "Estimated Arrival in $arrivalDay days"
    fun getPrice() = "$ ${Format(price.toInt())}"
}
