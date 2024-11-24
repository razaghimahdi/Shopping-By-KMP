package business.datasource.network.main.responses

import business.domain.main.Address
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddressDTO(
    @SerialName("id") val id: Long?,
    @SerialName("address") val address: String?,
    @SerialName("county") val country: String?,
    @SerialName("city") val city: String?,
    @SerialName("state") val state: String?,
    @SerialName("zip_code") val zipCode: String?
)

fun AddressDTO.toAddress() = Address(
    id = id ?: 0,
    address = address ?: "",
    country = country ?: "",
    city = city ?: "",
    state = state ?: "",
    zipCode = zipCode ?: ""
)