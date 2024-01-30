package presentation.ui.main.notifications.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class NotificationsEvent {


   data object OnRemoveHeadFromQueue : NotificationsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : NotificationsEvent()

   data object OnRetryNetwork : NotificationsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : NotificationsEvent()
}
