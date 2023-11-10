package presentation.ui.splash.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import moe.tlaster.precompose.viewmodel.ViewModel

class LoginViewModel : ViewModel() {


    private val TAG = "AppDebug LoginViewModel"


    val state: MutableState<LoginState> = mutableStateOf(LoginState())


    fun onTriggerEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }

            is LoginEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is LoginEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    private fun onUpdateNameRegister(value: String) {
        state.value = state.value.copy(nameRegister = value)
    }

    private fun onUpdatePasswordLogin(value: String) {
        state.value = state.value.copy(passwordLogin = value)
    }


    private fun onUpdateUsernameLogin(value: String) {
        state.value = state.value.copy(usernameLogin = value)
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