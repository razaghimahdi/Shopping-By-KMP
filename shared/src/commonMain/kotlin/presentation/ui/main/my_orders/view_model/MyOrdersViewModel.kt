package presentation.ui.main.my_orders.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.constants.SHIPPING_ACTIVE
import business.constants.SHIPPING_FAILED
import business.constants.SHIPPING_SUCCESS
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Address
import business.domain.main.Order
import business.domain.main.product_sample
import moe.tlaster.precompose.viewmodel.ViewModel
import presentation.ui.main.checkout.view_model.shippingType

class MyOrdersViewModel : ViewModel() {


    val state: MutableState<MyOrdersState> = mutableStateOf(MyOrdersState())


    fun onTriggerEvent(event: MyOrdersEvent) {
        when (event) {


            is MyOrdersEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is MyOrdersEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is MyOrdersEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MyOrdersEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        state.value = state.value.copy(
            orders = listOf(
                Order(
                    id = 0,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_ACTIVE,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
                Order(
                    id = 3,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_ACTIVE,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
                Order(
                    id = 1,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_SUCCESS,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
                Order(
                    id = 4,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_SUCCESS,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
                Order(
                    id = 2,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_FAILED,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
                Order(
                    id = 5,
                    products = listOf(product_sample, product_sample),
                    status = SHIPPING_FAILED,
                    code = "SFwfsSFSFWS",
                    createAt = "1h",
                    amount = "$ 512",
                    
                    address = Address(
                        address = "Alison Ave, Holly St",
                        country = "USA",
                        state = "NC",
                        city = "NC",
                        zipCode = "53543"
                    ),
                    shippingType = shippingType.first()
                ),
            )
        )
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
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
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}