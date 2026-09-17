package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.presentation.util.Format

data class ShippingType(val title: String, val price: Long, val arrivalDay: Int){
    fun getEstimatedDay() = "Estimated Arrival in $arrivalDay days"
    fun getPrice() = "$ ${Format(price.toInt())}"
}
