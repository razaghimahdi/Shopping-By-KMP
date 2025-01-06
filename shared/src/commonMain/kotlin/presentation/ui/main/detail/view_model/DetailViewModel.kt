package presentation.ui.main.detail.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.AddBasketInteractor
import business.interactors.main.LikeInteractor
import business.interactors.main.ProductInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailViewModel(
    private val productInteractor: ProductInteractor,
    private val addBasketInteractor: AddBasketInteractor,
    private val likeInteractor: LikeInteractor,
) : BaseViewModel<DetailEvent, DetailState, Nothing>() {

    override fun setInitialState() = DetailState()

    override fun onTriggerEvent(event: DetailEvent) {
        when (event) {

            is DetailEvent.Like -> {
                likeProduct(id = event.id)
            }

            is DetailEvent.AddBasket -> {
                addBasket(id = event.id)
            }

            is DetailEvent.OnUpdateSelectedImage -> {
                onUpdateSelectedImage(event.value)
            }

            is DetailEvent.GetProduct -> {
                getProduct(event.id)
            }

            is DetailEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is DetailEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    private fun onUpdateSelectedImage(value: String) {
        setState { copy(selectedImage = value) }
    }


    private fun likeProduct(id: Long) {
        likeInteractor.execute(id = id)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            if (it) updateLike()
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun addBasket(id: Long) {
        addBasketInteractor.execute(id = id, 1)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {}

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun updateLike() {
        setState { copy(product = state.value.product.copy(isLike = !state.value.product.isLike)) }
    }


    private fun getProduct(id: Long) {
        productInteractor.execute(id = id).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(DetailEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState {
                            copy(
                                product = it,
                                selectedImage = it.gallery.firstOrNull() ?: ""
                            )
                        }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getProduct(state.value.product.id)
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}