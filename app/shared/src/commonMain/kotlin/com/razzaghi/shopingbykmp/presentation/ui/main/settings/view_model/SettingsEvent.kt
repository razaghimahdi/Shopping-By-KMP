package com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class SettingsEvent : ViewEvent {

    data object Logout : SettingsEvent()

    data object OnRetryNetwork : SettingsEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : SettingsEvent()
}
