package presentation.ui.main.my_coupons.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class MyCouponsEvent {


   data object OnRemoveHeadFromQueue : MyCouponsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : MyCouponsEvent()

   data object OnRetryNetwork : MyCouponsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyCouponsEvent()
}
