package presentation.ui.main.settings.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class SettingsEvent : ViewEvent {

    data object Logout : SettingsEvent()

    data object OnRetryNetwork : SettingsEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : SettingsEvent()
}
