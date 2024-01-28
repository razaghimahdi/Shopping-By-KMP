package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AddressRequestDTO(
    @SerialName("address")  val address: String,
    @SerialName("county")  val country: String,
    @SerialName("city")  val city: String,
    @SerialName("state")  val state: String,
    @SerialName("zip_code")  val zipCode: String
)