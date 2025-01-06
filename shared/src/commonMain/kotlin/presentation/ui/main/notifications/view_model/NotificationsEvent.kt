package presentation.ui.main.notifications.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class NotificationsEvent : ViewEvent {

    data object OnRetryNetwork : NotificationsEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : NotificationsEvent()
}
