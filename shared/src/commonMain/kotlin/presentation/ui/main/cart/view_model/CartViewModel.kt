package presentation.ui.main.cart.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.AddBasketInteractor
import business.interactors.main.BasketListInteractor
import business.interactors.main.DeleteBasketInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CartViewModel(
    private val basketListInteractor: BasketListInteractor,
    private val addBasketInteractor: AddBasketInteractor,
    private val deleteBasketInteractor: DeleteBasketInteractor,
) : BaseViewModel<CartEvent, CartState, Nothing>() {

    override fun setInitialState() = CartState()

    override fun onTriggerEvent(event: CartEvent) {
        when (event) {

            is CartEvent.AddProduct -> {
                addProduct(id = event.id)
            }

            is CartEvent.DeleteFromBasket -> {
                deleteFromBasket(id = event.id)
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(baskets = it) }
                        val totalCost = state.value.baskets.sumOf { basket ->
                            basket.price
                        }
                        setState { copy(totalCost = "$ $totalCost") }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteFromBasket(id: Long) {
        deleteBasketInteractor.execute(id = id).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    if (dataState.data == true) onTriggerEvent(CartEvent.OnRetryNetwork)
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun addProduct(id: Long) {
        addBasketInteractor.execute(id = id, count = 1).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    if (dataState.data == true) onTriggerEvent(CartEvent.OnRetryNetwork)
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun onRetryNetwork() {
        getCart()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}