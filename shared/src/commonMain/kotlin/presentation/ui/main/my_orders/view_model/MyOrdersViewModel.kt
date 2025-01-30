package presentation.ui.main.my_orders.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.GetOrdersUseCase

class MyOrdersViewModel(
    private val getOrdersUseCase: GetOrdersUseCase
) : BaseViewModel<MyOrdersEvent, MyOrdersState, Nothing>() {

    override fun setInitialState() = MyOrdersState()

    override fun onTriggerEvent(event: MyOrdersEvent) {
        when (event) {

            is MyOrdersEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MyOrdersEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getOrders()
    }

    private fun getOrders() {
        executeUseCase(getOrdersUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(orders = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(MyOrdersEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun onRetryNetwork() {
        getOrders()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}