package presentation.ui.main.payment_method.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class PaymentMethodEvent {

    data class OnUpdateSelectedPaymentMethod(val value: Int) : PaymentMethodEvent()


   data object OnRemoveHeadFromQueue : PaymentMethodEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : PaymentMethodEvent()

   data object OnRetryNetwork : PaymentMethodEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : PaymentMethodEvent()
}
