package presentation.ui.main.notifications.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.constants.LOREM
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Notification
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import business.core.DataState
import business.interactors.main.GetNotificationsInteractor
import business.interactors.main.GetOrdersInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import presentation.ui.main.my_orders.view_model.MyOrdersEvent

class NotificationsViewModel(
    private val getNotificationsInteractor: GetNotificationsInteractor
) : ViewModel() {


    val state: MutableState<NotificationsState> = mutableStateOf(NotificationsState())


    fun onTriggerEvent(event: NotificationsEvent) {
        when (event) {


            is NotificationsEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is NotificationsEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

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
                    onTriggerEvent(NotificationsEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(notifications = dataState.data ?: listOf())
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getNotifications()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}