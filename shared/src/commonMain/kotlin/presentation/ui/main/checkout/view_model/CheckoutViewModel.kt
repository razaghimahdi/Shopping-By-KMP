package presentation.ui.main.checkout.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.ShippingType
import business.interactors.main.BasketListInteractor
import business.interactors.main.BuyProductInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

val shippingType_global = listOf(
    ShippingType("Economy", 25, 7),
    ShippingType("Regular", 35, 6),
    ShippingType("Cargo", 45, 5),
    ShippingType("Express", 55, 4)
)

class CheckoutViewModel(
    private val getAddressesInteractor: GetAddressesInteractor,
    private val basketListInteractor: BasketListInteractor,
    private val buyProductInteractor: BuyProductInteractor,
) : ViewModel() {


    val state: MutableState<CheckoutState> = mutableStateOf(CheckoutState())


    fun onTriggerEvent(event: CheckoutEvent) {
        when (event) {

            is CheckoutEvent.BuyProduct -> {
                buyProduct()
            }

            is CheckoutEvent.OnUpdateSelectedShipping -> {
                onUpdateSelectedShipping(event.value)
            }

            is CheckoutEvent.OnUpdateSelectShippingDialogState -> {
                onUpdateSelectShippingDialogState(event.value)
            }

            is CheckoutEvent.OnUpdateSelectedAddress -> {
                onUpdateSelectedAddress(event.value)
            }

            is CheckoutEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is CheckoutEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is CheckoutEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CheckoutEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    init {
        getAddresses()
        getCart()
    }

    private fun buyProduct() {
        buyProductInteractor.execute(
            addressId = state.value.selectedAddress.id,
            shippingType = shippingType_global.indexOf(state.value.selectedShipping)
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CheckoutEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    onTriggerEvent(CheckoutEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(buyingSuccess = it)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCart() {
        basketListInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CheckoutEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    onTriggerEvent(CheckoutEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        val totalCost = it.sumOf { basket ->
                            basket.price
                        }
                        state.value = state.value.copy(totalBasket = totalCost)
                        state.value =
                            state.value.copy(totalCost = totalCost + state.value.selectedShipping.price)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAddresses() {
        getAddressesInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CheckoutEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    onTriggerEvent(CheckoutEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(addresses = it)
                        it.firstOrNull()?.let { address ->
                            state.value = state.value.copy(selectedAddress = address)
                        }
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectShippingDialogState(value: UIComponentState) {
        state.value = state.value.copy(selectShippingDialogState = value)

        state.value =
            state.value.copy(totalCost = state.value.totalBasket + state.value.selectedShipping.price)
    }

    private fun onUpdateSelectedShipping(value: ShippingType) {
        state.value = state.value.copy(selectedShipping = value)
    }

    private fun onUpdateSelectedAddress(value: Address) {
        state.value = state.value.copy(selectedAddress = value)
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
        getAddresses()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}