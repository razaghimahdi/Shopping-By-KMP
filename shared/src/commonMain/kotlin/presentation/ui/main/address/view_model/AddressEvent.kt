package presentation.ui.main.address.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState

sealed class AddressEvent {

    data class OnUpdateAddAddressDialogState(val value: UIComponentState) : AddressEvent()

    data class AddAddress(
        val address: String,
        val country: String,
        val city: String,
        val state: String,
        val zipCode: String,
    ) : AddressEvent()

   data object OnRemoveHeadFromQueue : AddressEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : AddressEvent()

   data object OnRetryNetwork : AddressEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : AddressEvent()
}
