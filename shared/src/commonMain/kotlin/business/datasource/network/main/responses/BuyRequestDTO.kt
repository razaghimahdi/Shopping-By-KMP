package business.datasource.network.main.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BuyRequestDTO(
    @SerialName("address_id")  val addressId: Long,
    @SerialName("shipping_type")  val shippingType: Int,
    )
