package com.razzaghi.interactor

import com.razzaghi.datasource.network.common.JAlertResponse


const val THROWABLE_DIVIDER = "THROWABLE_DIVIDER"

fun JAlertResponse.createException() =  Throwable(" $THROWABLE_DIVIDER ${this.title} $THROWABLE_DIVIDER ${this.message}")