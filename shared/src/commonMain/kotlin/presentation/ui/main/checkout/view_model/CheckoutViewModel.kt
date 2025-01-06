package presentation.ui.main.checkout.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.ShippingType
import business.interactors.main.BasketListInteractor
import business.interactors.main.BuyProductInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
) : BaseViewModel<CheckoutEvent, CheckoutState, CheckoutAction>() {

    override fun setInitialState() = CheckoutState()


    override fun onTriggerEvent(event: CheckoutEvent) {
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        if (it) {
                            setAction { CheckoutAction.Navigation.PopUp }
                        }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        val totalCost = it.sumOf { basket ->
                            basket.price
                        }
                        setState {
                            copy(
                                totalBasket = totalCost,
                                totalCost = totalCost + state.value.selectedShipping.price
                            )
                        }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(addresses = it) }
                        it.firstOrNull()?.let { address ->
                            setState { copy(selectedAddress = address) }
                        }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectShippingDialogState(value: UIComponentState) {
        setState {
            copy(
                selectShippingDialogState = value,
                totalCost = state.value.totalBasket + state.value.selectedShipping.price
            )
        }
    }

    private fun onUpdateSelectedShipping(value: ShippingType) {
        setState { copy(selectedShipping = value) }
    }

    private fun onUpdateSelectedAddress(value: Address) {
        setState { copy(selectedAddress = value) }
    }


    private fun onRetryNetwork() {
        getAddresses()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}