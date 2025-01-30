package presentation.ui.main.checkout.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.ShippingType
import business.interactors.main.BasketListUseCase
import business.interactors.main.BuyProductUseCase
import business.interactors.main.GetAddressesUseCase

val shippingType_global = listOf(
    ShippingType("Economy", 25, 7),
    ShippingType("Regular", 35, 6),
    ShippingType("Cargo", 45, 5),
    ShippingType("Express", 55, 4)
)

class CheckoutViewModel(
    private val getAddressesUseCase: GetAddressesUseCase,
    private val basketListUseCase: BasketListUseCase,
    private val buyProductUseCase: BuyProductUseCase,
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
        executeUseCase(buyProductUseCase.execute(
            BuyProductUseCase.Params(
                addressId = state.value.selectedAddress.id,
                shippingType = shippingType_global.indexOf(state.value.selectedShipping)
            )
        ), onSuccess = {
            it?.let {
                if (it) {
                    setAction { CheckoutAction.Navigation.PopUp }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun getCart() {
        executeUseCase(basketListUseCase.execute(Unit), onSuccess = {
            it?.let {
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
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun getAddresses() {
        executeUseCase(getAddressesUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(addresses = it) }
                it.firstOrNull()?.let { address ->
                    setState { copy(selectedAddress = address) }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CheckoutEvent.OnUpdateNetworkState(it))
        }
        )
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