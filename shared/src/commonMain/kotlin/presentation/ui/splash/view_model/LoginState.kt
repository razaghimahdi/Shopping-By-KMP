package presentation.ui.splash.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState

data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    ) : ViewState
