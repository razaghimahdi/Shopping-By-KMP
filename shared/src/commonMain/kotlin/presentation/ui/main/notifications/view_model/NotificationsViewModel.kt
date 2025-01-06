package presentation.ui.main.notifications.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.GetNotificationsInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NotificationsViewModel(
    private val getNotificationsInteractor: GetNotificationsInteractor
) : BaseViewModel<NotificationsEvent, NotificationsState, Nothing>() {

    override fun setInitialState()=NotificationsState()

    override fun onTriggerEvent(event: NotificationsEvent) {
        when (event) {

            is NotificationsEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is NotificationsEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getNotifications()
    }


    private fun getNotifications() {

        getNotificationsInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(NotificationsEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    setState { copy(notifications = dataState.data ?: listOf()) }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getNotifications()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}