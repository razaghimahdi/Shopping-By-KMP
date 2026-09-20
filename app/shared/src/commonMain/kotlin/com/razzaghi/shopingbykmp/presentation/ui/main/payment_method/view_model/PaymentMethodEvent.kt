package com.razzaghi.shopingbykmp.presentation.ui.main.payment_method.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class PaymentMethodEvent : ViewEvent {

    data class OnUpdateSelectedPaymentMethod(val value: Int) : PaymentMethodEvent()

    data object OnRetryNetwork : PaymentMethodEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : PaymentMethodEvent()
}
