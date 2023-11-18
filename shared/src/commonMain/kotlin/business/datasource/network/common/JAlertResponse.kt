package business.datasource.network.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class JAlertResponse(
    @SerialName("title") var title: String = "",
    @SerialName("message") var message: String = ""
)