package presentation.ui.main.my_coupons.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class MyCouponsEvent : ViewEvent {

    data object OnRetryNetwork : MyCouponsEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyCouponsEvent()
}
