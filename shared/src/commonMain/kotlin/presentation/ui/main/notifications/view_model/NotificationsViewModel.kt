package presentation.ui.main.notifications.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.LOREM
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Notification
import business.interactors.main.AddAddressInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class NotificationsViewModel() : ViewModel() {


    private val TAG = "AppDebug NotificationsViewModel"


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
        state.value = state.value.copy(notifications = listOf(
            Notification(title = "Order Shipped",desc = LOREM, createAt = "1h", isRead = false),
            Notification(title = "Flash Sale Alert",desc = LOREM, createAt = "3h", isRead = true),
            Notification(title = "Product Review Request",desc = LOREM, createAt = "5h", isRead = true),
            Notification(title = "New Paypal Added",desc = LOREM, createAt = "5h", isRead = true),
        ))

    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${TAG}: onTriggerEvent:  ${(uiComponent as UIComponent.None).message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}