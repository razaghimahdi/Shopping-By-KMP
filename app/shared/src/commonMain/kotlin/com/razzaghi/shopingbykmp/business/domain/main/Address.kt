package com.razzaghi.shopingbykmp.business.domain.main

import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressDTO

data class Address(
    val id: Long = 0,
    val address: String = "",
    val country: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = ""
) {
    fun getFullAddress() = "$state, $city, $address"
    fun getLocation() = if (city.isEmpty()) "No Location!" else "$city, $country"

    fun getShippingAddress() =
        if (address.isEmpty() && city.isEmpty()) "No Location!" else "$address, $state, $city, $country \n$zipCode"
}



fun AddressDTO.toAddress() = Address(
    id = id ?: 0,
    address = address ?: "",
    country = country ?: "",
    city = city ?: "",
    state = state ?: "",
    zipCode = zipCode ?: ""
)