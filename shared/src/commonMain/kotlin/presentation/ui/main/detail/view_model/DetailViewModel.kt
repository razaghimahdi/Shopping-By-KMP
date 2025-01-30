package presentation.ui.main.detail.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.AddBasketUseCase
import business.interactors.main.LikeUseCase
import business.interactors.main.ProductUseCase

class DetailViewModel(
    private val productUseCase: ProductUseCase,
    private val addBasketUseCase: AddBasketUseCase,
    private val likeUseCase: LikeUseCase,
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
        executeUseCase(likeUseCase.execute(LikeUseCase.Params(id = id)), onSuccess = {
            it?.let {
                if (it) updateLike()
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }


    private fun addBasket(id: Long) {
        executeUseCase(addBasketUseCase.execute(AddBasketUseCase.Params(id = id, 1)), onSuccess = {
            it?.let {
                if (it) updateLike()
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }


    private fun updateLike() {
        setState { copy(product = state.value.product.copy(isLike = !state.value.product.isLike)) }
    }


    private fun getProduct(id: Long) {
        executeUseCase(productUseCase.execute(ProductUseCase.Params(id = id)), onSuccess = {
            it?.let {
                setState {
                    copy(
                        product = it,
                        selectedImage = it.gallery.firstOrNull() ?: ""
                    )
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(DetailEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun onRetryNetwork() {
        getProduct(state.value.product.id)
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}