package presentation.ui.main.wishlist.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.domain.main.Category
import business.domain.main.category_all
import business.interactors.main.LikeInteractor
import business.interactors.main.WishListInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WishlistViewModel(
    private val wishListInteractor: WishListInteractor,
    private val likeInteractor: LikeInteractor,
) : BaseViewModel<WishlistEvent, WishlistState, Nothing>() {

    override fun setInitialState() = WishlistState()

    override fun onTriggerEvent(event: WishlistEvent) {
        when (event) {

            is WishlistEvent.LikeProduct -> {
                likeProduct(id = event.id)
            }

            is WishlistEvent.GetNextPage -> {
                getNextPage()
            }

            is WishlistEvent.OnUpdateSelectedCategory -> {
                onUpdateSelectedCategory(category = event.category)
            }

            is WishlistEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is WishlistEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getWishlist()
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
                            if (it) {
                                val currentList = state.value.wishlist.products.toMutableList()
                                val item = currentList.find { product ->
                                    product.id == id
                                }
                                currentList.remove(item)
                                setState { copy(wishlist = state.value.wishlist.copy(products = currentList)) }
                            }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getWishlist() {
        wishListInteractor.execute(categoryId = state.value.categoryId, page = 1)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {
                        onTriggerEvent(WishlistEvent.OnUpdateNetworkState(dataState.networkState))
                    }

                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {

                            val categories = it.categories.toMutableList()
                            categories.add(0, category_all)

                            setState { copy(wishlist = it.copy(categories = categories)) }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun getNextPage() {
        setState { copy(page = state.value.page + 1) }
        wishListInteractor.execute(categoryId = state.value.categoryId, page = state.value.page)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState { copy(wishlist = state.value.wishlist.copy(products = it.products)) }
                            if (it.products.isEmpty()) {
                                setState { copy(hasNextPage = false) }
                            }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectedCategory(category: Category) {
        setState { copy(selectedCategory = category) }
        getWishlist()
    }

    private fun onRetryNetwork() {
        getWishlist()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}