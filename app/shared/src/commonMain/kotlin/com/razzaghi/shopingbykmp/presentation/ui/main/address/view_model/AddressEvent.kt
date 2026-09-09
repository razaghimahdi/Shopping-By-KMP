package presentation.ui.main.address.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import business.core.ViewEvent

sealed class AddressEvent : ViewEvent {

    data object OnRetryNetwork : AddressEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : AddressEvent()
}
