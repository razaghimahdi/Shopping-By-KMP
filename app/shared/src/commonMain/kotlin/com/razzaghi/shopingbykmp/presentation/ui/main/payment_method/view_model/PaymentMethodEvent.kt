package presentation.ui.main.payment_method.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class PaymentMethodEvent : ViewEvent {

    data class OnUpdateSelectedPaymentMethod(val value: Int) : PaymentMethodEvent()

    data object OnRetryNetwork : PaymentMethodEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : PaymentMethodEvent()
}
