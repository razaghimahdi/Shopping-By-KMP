package com.razzaghi.shopingbykmp.presentation.ui.main.notifications.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Notification

data class NotificationsState(
    val notifications: List<Notification> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
