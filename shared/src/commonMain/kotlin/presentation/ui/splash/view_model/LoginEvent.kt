package presentation.ui.splash.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class LoginEvent{


    data class OnUpdateNameRegister(val value: String) : LoginEvent()
    data class OnUpdateUsernameLogin(val value: String) : LoginEvent()
    data class OnUpdatePasswordLogin(val value: String) : LoginEvent()

    object Register : LoginEvent()
    object Login : LoginEvent()
    object OnRemoveHeadFromQueue : LoginEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : LoginEvent()


    object OnRetryNetwork : LoginEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ): LoginEvent()
}
