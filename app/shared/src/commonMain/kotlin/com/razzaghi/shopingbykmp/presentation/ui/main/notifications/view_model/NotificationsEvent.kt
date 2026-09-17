package com.razzaghi.shopingbykmp.presentation.ui.main.notifications.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class NotificationsEvent : ViewEvent {

    data object OnRetryNetwork : NotificationsEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : NotificationsEvent()
}
