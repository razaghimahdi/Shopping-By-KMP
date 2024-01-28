package presentation.ui.main.payment_method.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.interactors.main.AddAddressInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class PaymentMethodViewModel() : ViewModel() {


    private val TAG = "AppDebug PaymentMethodViewModel"


    val state: MutableState<PaymentMethodState> = mutableStateOf(PaymentMethodState())


    fun onTriggerEvent(event: PaymentMethodEvent) {
        when (event) {


            is PaymentMethodEvent.OnUpdateSelectedPaymentMethod -> {
                onUpdateSelectedPaymentMethod(event.value)
            }

            is PaymentMethodEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is PaymentMethodEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
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
        state.value = state.value.copy(selectedPaymentMethod = value)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${TAG}: onTriggerEvent:  ${(uiComponent as UIComponent.None).message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}