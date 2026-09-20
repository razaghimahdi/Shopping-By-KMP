package com.razzaghi.shopingbykmp.presentation.ui.splash.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewState

data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    ) : ViewState
