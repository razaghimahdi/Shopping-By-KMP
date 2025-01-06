package presentation.ui.main.home.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class HomeEvent : ViewEvent {

    data object OnRetryNetwork : HomeEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : HomeEvent()

    data class Like(val id: Long) : HomeEvent()

}
