package presentation.ui.main.cart.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class CartEvent {

    data class DeleteFromBasket(val id: Int) : CartEvent()

    object OnRemoveHeadFromQueue : CartEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : CartEvent()

    object OnRetryNetwork : CartEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CartEvent()
}
