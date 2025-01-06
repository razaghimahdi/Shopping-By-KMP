package presentation.ui.main.profile.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class ProfileEvent : ViewEvent {

    data object OnRetryNetwork : ProfileEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : ProfileEvent()

}
