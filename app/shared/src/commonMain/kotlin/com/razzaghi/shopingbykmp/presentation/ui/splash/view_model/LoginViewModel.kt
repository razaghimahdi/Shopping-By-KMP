package presentation.ui.splash.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.splash.CheckTokenUseCase
import business.interactors.splash.LoginUseCase
import business.interactors.splash.RegisterUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val checkTokenUseCase: CheckTokenUseCase,
) : BaseViewModel<LoginEvent, LoginState, LoginAction>() {

    override fun setInitialState() = LoginState()

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.Login -> {
                login()
            }

            is LoginEvent.Register -> {
                register()
            }

            is LoginEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }

            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        checkToken()
    }

    private fun checkToken() {
        executeUseCase(checkTokenUseCase.execute(Unit), onSuccess = {
            it?.let {

                setState {
                    copy(isTokenValid = it)
                }

                setAction {
                    if (it) {
                        LoginAction.Navigation.NavigateToMain
                    } else {
                        LoginAction.Navigation.NavigateToLogin
                    }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }

    private fun login() {
        executeUseCase(loginUseCase.execute(
            LoginUseCase.Params(
                email = state.value.usernameLogin,
                password = state.value.passwordLogin,
            )
        ), onSuccess = {
            it?.let {
                setAction {
                    if (it.isNotEmpty()) {
                        LoginAction.Navigation.NavigateToMain
                    } else {
                        LoginAction.Navigation.NavigateToLogin
                    }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }

    private fun register() {
        executeUseCase(registerUseCase.execute(
            RegisterUseCase.Params(
                email = state.value.usernameLogin,
                password = state.value.passwordLogin,
                name = state.value.nameRegister
            )
        ), onSuccess = {
            it?.let {
                setAction {
                    if (it.isNotEmpty()) {
                        LoginAction.Navigation.NavigateToMain
                    } else {
                        LoginAction.Navigation.NavigateToLogin
                    }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }


    private fun onUpdateNameRegister(value: String) {
        setState {
            copy(nameRegister = value)
        }
    }

    private fun onUpdatePasswordLogin(value: String) {
        setState {
            copy(passwordLogin = value)
        }
    }

    private fun onUpdateUsernameLogin(value: String) {
        setState {
            copy(usernameLogin = value)
        }
    }

    private fun onRetryNetwork() {
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState {
            copy(networkState = networkState)
        }
    }

}