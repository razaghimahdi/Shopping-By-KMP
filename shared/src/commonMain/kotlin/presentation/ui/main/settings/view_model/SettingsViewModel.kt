package presentation.ui.main.settings.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.LogoutUseCase

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
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
        executeUseCase(logoutUseCase.execute(Unit), onSuccess = {
            it?.let {
                setAction { SettingsAction.Navigation.PopUp }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }

    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}