package presentation.ui.main.cart.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.AddBasketUseCase
import business.interactors.main.BasketListUseCase
import business.interactors.main.DeleteBasketUseCase

class CartViewModel(
    private val basketListUseCase: BasketListUseCase,
    private val addBasketUseCase: AddBasketUseCase,
    private val deleteBasketUseCase: DeleteBasketUseCase,
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
        executeUseCase(basketListUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(baskets = it) }
                val totalCost = state.value.baskets.sumOf { basket ->
                    basket.price
                }
                setState { copy(totalCost = "$ $totalCost") }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CartEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun deleteFromBasket(id: Long) {
        executeUseCase(
            deleteBasketUseCase.execute(DeleteBasketUseCase.Params(id = id)),
            onSuccess = {
                it?.let {
                    if (it) setEvent(CartEvent.OnRetryNetwork)
                }
            },
            onLoading = {
                setState { copy(progressBarState = it) }
            }
        )
    }


    private fun addProduct(id: Long) {
        executeUseCase(
            addBasketUseCase.execute(AddBasketUseCase.Params(id = id, count = 1)),
            onSuccess = {
                it?.let {
                    if (it) setEvent(CartEvent.OnRetryNetwork)
                }
            },
            onLoading = {
                setState { copy(progressBarState = it) }
            }
        )
    }


    private fun onRetryNetwork() {
        getCart()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}