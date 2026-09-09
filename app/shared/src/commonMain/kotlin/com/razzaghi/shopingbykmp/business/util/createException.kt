package business.util

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse


const val THROWABLE_DIVIDER = "THROWABLE_DIVIDER"

fun JAlertResponse.createException() =  Throwable(" $THROWABLE_DIVIDER ${this.title} $THROWABLE_DIVIDER ${this.message}")