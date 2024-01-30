package presentation.ui.main.cart.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.interactors.main.AddBasketInteractor
import business.interactors.main.BasketListInteractor
import business.interactors.main.DeleteBasketInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class CartViewModel(
    private val basketListInteractor: BasketListInteractor,
    private val addBasketInteractor: AddBasketInteractor,
    private val deleteBasketInteractor: DeleteBasketInteractor,
) : ViewModel() {


    val state: MutableState<CartState> = mutableStateOf(CartState())


    fun onTriggerEvent(event: CartEvent) {
        when (event) {

            is CartEvent.AddProduct -> {
                addProduct(id = event.id)
            }

            is CartEvent.DeleteFromBasket -> {
                deleteFromBasket(id = event.id)
            }

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
        getCart()
    }

    private fun getCart() {
        basketListInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CartEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    onTriggerEvent(CartEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(baskets = it)
                        val totalCost = state.value.baskets.sumOf { basket ->
                            basket.price
                        }
                        state.value = state.value.copy(totalCost = "$ $totalCost")
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteFromBasket(id: Int) {
        deleteBasketInteractor.execute(id = id).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(CartEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    if (dataState.data == true) onTriggerEvent(CartEvent.OnRetryNetwork)
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun addProduct(id: Int) {
        addBasketInteractor.execute(id = id, count = 1).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(CartEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    if (dataState.data == true) onTriggerEvent(CartEvent.OnRetryNetwork)
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
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getCart()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}