package presentation.ui.main.my_orders.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.GetOrdersInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MyOrdersViewModel(
    private val getOrdersInteractor: GetOrdersInteractor
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

        getOrdersInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(MyOrdersEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    setState { copy(orders = dataState.data ?: listOf()) }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getOrders()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}