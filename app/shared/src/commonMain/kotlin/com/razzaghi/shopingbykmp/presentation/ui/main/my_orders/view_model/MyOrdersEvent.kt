package presentation.ui.main.my_orders.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class MyOrdersEvent : ViewEvent {

    data object OnRetryNetwork : MyOrdersEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyOrdersEvent()
}
