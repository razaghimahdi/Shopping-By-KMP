package presentation.ui.main.edit_profile.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class EditProfileEvent {
    object OnRemoveHeadFromQueue : EditProfileEvent()

    data class OnUpdateName(val value: String) : EditProfileEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : EditProfileEvent()

    object OnRetryNetwork : EditProfileEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : EditProfileEvent()


}
