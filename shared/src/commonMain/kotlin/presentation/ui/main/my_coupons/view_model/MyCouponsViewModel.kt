package presentation.ui.main.my_coupons.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Coupons
import moe.tlaster.precompose.viewmodel.ViewModel

class MyCouponsViewModel : ViewModel() {



    val state: MutableState<MyCouponsState> = mutableStateOf(MyCouponsState())


    fun onTriggerEvent(event: MyCouponsEvent) {
        when (event) {


            is MyCouponsEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is MyCouponsEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is MyCouponsEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MyCouponsEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        state.value = state.value.copy(couponsList = listOf(
            Coupons(title = "WELCOME24",desc = "Add items worth $2 more to unlock",code = "SDSD23SD", offPercent = 50),
            Coupons(title = "BLACKFRIDAY24",desc = "Add items worth $1 more to unlock",code = "S2323SD", offPercent = 75),
            Coupons(title = "HOLYDAY24",desc = "Add items worth $15 more to unlock",code = "SFER23SD", offPercent = 15),
            Coupons(title = "WELCOME24",desc = "Add items worth $2 more to unlock",code = "SDSD23SD", offPercent = 50),
            Coupons(title = "BLACKFRIDAY24",desc = "Add items worth $1 more to unlock",code = "S2323SD", offPercent = 75),
            Coupons(title = "HOLYDAY24",desc = "Add items worth $15 more to unlock",code = "SFER23SD", offPercent = 15),
        ))
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
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}