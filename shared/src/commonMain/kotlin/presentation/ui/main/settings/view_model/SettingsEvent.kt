package presentation.ui.main.settings.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class SettingsEvent {

   data object Logout : SettingsEvent()

   data object OnRemoveHeadFromQueue : SettingsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : SettingsEvent()

   data object OnRetryNetwork : SettingsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : SettingsEvent()
}
