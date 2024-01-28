package presentation.ui.main.notifications.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.Comment
import business.domain.main.Home
import business.domain.main.Notification
import business.domain.main.Product

data class NotificationsState(
    val notifications: List<Notification> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
