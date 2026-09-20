package com.razzaghi.shopingbykmp.business.datasource.network.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainGenericResponse<T>(
    @SerialName("result") var result: T? = null,
    @SerialName("status") var status: Boolean? = null,
    @SerialName("alert") var alert: JAlertResponse? = JAlertResponse(),
)