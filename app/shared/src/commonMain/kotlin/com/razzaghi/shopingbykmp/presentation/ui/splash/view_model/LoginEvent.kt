package com.razzaghi.shopingbykmp.presentation.ui.splash.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class LoginEvent : ViewEvent {
    data class OnUpdateNameRegister(val value: String) : LoginEvent()
    data class OnUpdateUsernameLogin(val value: String) : LoginEvent()
    data class OnUpdatePasswordLogin(val value: String) : LoginEvent()

    data object Register : LoginEvent()
    data object Login : LoginEvent()

    data object OnRetryNetwork : LoginEvent()
    data class OnUpdateNetworkState(val networkState: NetworkState) : LoginEvent()
}
