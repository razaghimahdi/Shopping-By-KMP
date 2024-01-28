package presentation.ui.main.payment_method.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import presentation.ui.main.home.view_model.HomeEvent

sealed class PaymentMethodEvent {

    data class OnUpdateSelectedPaymentMethod(val value: Int) : PaymentMethodEvent()


    object OnRemoveHeadFromQueue : PaymentMethodEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : PaymentMethodEvent()

    object OnRetryNetwork : PaymentMethodEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : PaymentMethodEvent()
}
