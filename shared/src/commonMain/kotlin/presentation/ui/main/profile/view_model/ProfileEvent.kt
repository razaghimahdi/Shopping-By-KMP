package presentation.ui.main.profile.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class ProfileEvent {
   data object OnRemoveHeadFromQueue : ProfileEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : ProfileEvent()

   data object OnRetryNetwork : ProfileEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : ProfileEvent()

}
