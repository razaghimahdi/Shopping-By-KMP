package presentation.ui.main.settings.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.LogoutInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SettingsViewModel(
    private val logoutInteractor: LogoutInteractor,
) : BaseViewModel<SettingsEvent, SettingsState, SettingsAction>() {

    override fun setInitialState() = SettingsState()

    override fun onTriggerEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.Logout -> {
                logout()
            }

            is SettingsEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is SettingsEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    private fun logout() {
        logoutInteractor.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            if (it) {
                                setAction { SettingsAction.Navigation.PopUp }
                            }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}