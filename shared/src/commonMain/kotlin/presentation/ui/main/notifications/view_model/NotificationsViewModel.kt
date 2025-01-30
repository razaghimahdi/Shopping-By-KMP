package presentation.ui.main.notifications.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.GetNotificationsUseCase

class NotificationsViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase
) : BaseViewModel<NotificationsEvent, NotificationsState, Nothing>() {

    override fun setInitialState() = NotificationsState()

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
        executeUseCase(getNotificationsUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(notifications = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(NotificationsEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun onRetryNetwork() {
        getNotifications()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}