package presentation.ui.main.categories.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.HomeInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoriesViewModel(
    private val homeInteractor: HomeInteractor,
) : BaseViewModel<CategoriesEvent, CategoriesState, Nothing>() {


    override fun setInitialState() = CategoriesState()


    override fun onTriggerEvent(event: CategoriesEvent) {
        when (event) {

            is CategoriesEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CategoriesEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getCategories()
    }


    private fun getCategories() {
        homeInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CategoriesEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(categories = it.categories) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getCategories()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}