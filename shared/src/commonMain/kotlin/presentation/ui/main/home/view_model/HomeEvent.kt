package presentation.ui.main.home.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class HomeEvent {


   data object OnRemoveHeadFromQueue : HomeEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : HomeEvent()

   data object OnRetryNetwork : HomeEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : HomeEvent()

    data class Like(val id: Int) : HomeEvent()

}
