package presentation.ui.main.cart.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class CartEvent : ViewEvent {

    data class DeleteFromBasket(val id: Long) : CartEvent()

    data class AddProduct(val id: Long) : CartEvent()

    data object OnRetryNetwork : CartEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CartEvent()
}
