package presentation.ui.main.my_coupons.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import presentation.ui.main.home.view_model.HomeEvent

sealed class MyCouponsEvent {


    object OnRemoveHeadFromQueue : MyCouponsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : MyCouponsEvent()

    object OnRetryNetwork : MyCouponsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyCouponsEvent()
}
