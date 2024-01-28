package presentation.ui.main.checkout.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.ShippingType
import presentation.ui.main.home.view_model.HomeEvent

sealed class CheckoutEvent {

    data class OnUpdateSelectedShipping(val value: ShippingType) : CheckoutEvent()

    data class OnUpdateSelectShippingDialogState(val value: UIComponentState) : CheckoutEvent()

    data class OnUpdateSelectedAddress(val value: Address) : CheckoutEvent()

    object OnRemoveHeadFromQueue : CheckoutEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : CheckoutEvent()

    object OnRetryNetwork : CheckoutEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CheckoutEvent()
}
