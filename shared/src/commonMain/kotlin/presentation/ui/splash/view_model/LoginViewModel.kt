package presentation.ui.splash.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.splash.CheckTokenInteractor
import business.interactors.splash.LoginInteractor
import business.interactors.splash.RegisterInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val registerInteractor: RegisterInteractor,
    private val checkTokenInteractor: CheckTokenInteractor,
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

//            is LoginEvent.OnRemoveHeadFromQueue -> {
//                removeHeadMessage()
//            }
//
//            is LoginEvent.Error -> {
//                appendToMessageQueue(event.uiComponent)
//            }

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
        checkTokenInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {

                    setState {
                        copy(isTokenValid = dataState.data ?: false)
                    }

                    setAction {
                        if (dataState.data == true) {
                            LoginAction.Navigation.NavigateToMain
                        } else {
                            LoginAction.Navigation.NavigateToLogin
                        }
                    }

                }

                is DataState.Loading -> {
                    setState {
                        copy(progressBarState = dataState.progressBarState)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun login() {
        loginInteractor.execute(
            email = state.value.usernameLogin,
            password = state.value.passwordLogin,
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    setAction {
                        if (!dataState.data.isNullOrEmpty()) {
                            LoginAction.Navigation.NavigateToMain
                        } else {
                            LoginAction.Navigation.NavigateToLogin
                        }
                    }
                }

                is DataState.Loading -> {
                    setState {
                        copy(progressBarState = dataState.progressBarState)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun register() {
        registerInteractor.execute(
            email = state.value.usernameLogin,
            password = state.value.passwordLogin,
            name = state.value.nameRegister
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    setAction {
                        if (!dataState.data.isNullOrEmpty()) {
                            LoginAction.Navigation.NavigateToMain
                        } else {
                            LoginAction.Navigation.NavigateToLogin
                        }
                    }
                }

                is DataState.Loading -> {
                    setState {
                        copy(progressBarState = dataState.progressBarState)
                    }
                }
            }
        }.launchIn(viewModelScope)
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