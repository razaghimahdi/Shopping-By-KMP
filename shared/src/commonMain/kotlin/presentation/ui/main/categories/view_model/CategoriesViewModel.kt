package presentation.ui.main.categories.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.HomeUseCase

class CategoriesViewModel(
    private val homeUseCase: HomeUseCase,
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
        executeUseCase(homeUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(categories = it.categories) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(CategoriesEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun onRetryNetwork() {
        getCategories()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}