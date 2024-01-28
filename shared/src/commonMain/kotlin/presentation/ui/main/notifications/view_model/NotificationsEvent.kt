package presentation.ui.main.notifications.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import presentation.ui.main.home.view_model.HomeEvent

sealed class NotificationsEvent {


    object OnRemoveHeadFromQueue : NotificationsEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : NotificationsEvent()

    object OnRetryNetwork : NotificationsEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : NotificationsEvent()
}
