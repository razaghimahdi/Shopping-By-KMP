package presentation.ui.main.settings.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import presentation.ui.main.home.view_model.HomeEvent

sealed class SettingsEvent {

    object Logout : SettingsEvent()

    object OnRemoveHeadFromQueue : SettingsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : SettingsEvent()

    object OnRetryNetwork : SettingsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : SettingsEvent()
}
