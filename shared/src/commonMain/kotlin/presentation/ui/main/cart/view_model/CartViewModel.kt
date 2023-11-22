package presentation.ui.main.cart.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.interactors.main.HomeInteractor
import business.interactors.splash.CheckTokenInteractor
import business.interactors.splash.LoginInteractor
import business.interactors.splash.RegisterInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class CartViewModel(
    
) : ViewModel() {


    private val TAG = "AppDebug CartViewModel"


    val state: MutableState<CartState> = mutableStateOf(CartState())


    fun onTriggerEvent(event: CartEvent) {
        when (event) {

            is CartEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is CartEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is CartEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CartEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getWishlist()
    }

    private fun getWishlist() {/*
        homeInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(CartEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(home = it)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)*/
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
        getWishlist()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}