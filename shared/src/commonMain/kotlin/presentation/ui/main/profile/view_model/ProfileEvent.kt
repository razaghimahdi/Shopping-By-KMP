package presentation.ui.main.profile.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class ProfileEvent {
    object OnRemoveHeadFromQueue : ProfileEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : ProfileEvent()

    object OnRetryNetwork : ProfileEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : ProfileEvent()

}
