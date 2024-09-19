package com.razzaghi.datasource.network.common


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MainGenericResponse<T>(
    @SerialName("result") var result: T?,
    @SerialName("status") var status: Boolean?,
    @SerialName("alert") var alert: com.razzaghi.datasource.network.common.JAlertResponse? = com.razzaghi.datasource.network.common.JAlertResponse(),
)