package presentation.ui.main.payment_method.view_model

import business.core.BaseViewModel
import business.core.NetworkState

class PaymentMethodViewModel : BaseViewModel<PaymentMethodEvent, PaymentMethodState, Nothing>() {

    override fun setInitialState() = PaymentMethodState()


    override fun onTriggerEvent(event: PaymentMethodEvent) {
        when (event) {


            is PaymentMethodEvent.OnUpdateSelectedPaymentMethod -> {
                onUpdateSelectedPaymentMethod(event.value)
            }

            is PaymentMethodEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is PaymentMethodEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    private fun onUpdateSelectedPaymentMethod(value: Int) {
        setState { copy(selectedPaymentMethod = value) }
    }

    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }

}