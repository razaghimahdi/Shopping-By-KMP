package presentation.ui.main.wishlist.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.domain.main.Category
import business.domain.main.category_all
import business.interactors.main.LikeUseCase
import business.interactors.main.WishListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import presentation.ui.main.address.view_model.AddressEvent

class WishlistViewModel(
    private val wishListUseCase: WishListUseCase,
    private val likeUseCase: LikeUseCase,
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
        executeUseCase(likeUseCase.execute(LikeUseCase.Params(id = id)), onSuccess = {
            it?.let {
                if (it) {
                    val currentList = state.value.wishlist.products.toMutableList()
                    val item = currentList.find { product ->
                        product.id == id
                    }
                    currentList.remove(item)
                    setState { copy(wishlist = state.value.wishlist.copy(products = currentList)) }
                }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }

    private fun getWishlist() {
        executeUseCase(
            wishListUseCase.execute(
                WishListUseCase.Params(
                    categoryId = state.value.categoryId,
                    page = 1
                )
            ), onSuccess = {
                it?.let {
                    val categories = it.categories.toMutableList()
                    categories.add(0, category_all)

                    setState { copy(wishlist = it.copy(categories = categories)) }
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }, onNetworkStatus = {
                setEvent(WishlistEvent.OnUpdateNetworkState(it))
            }
        )
    }


    private fun getNextPage() {
        setState { copy(page = state.value.page + 1) }
        executeUseCase(
            wishListUseCase.execute(
                WishListUseCase.Params(
                    categoryId = state.value.categoryId,
                    page = state.value.page
                )
            ), onSuccess = {
                it?.let {
                    setState { copy(wishlist = state.value.wishlist.copy(products = it.products)) }
                    if (it.products.isEmpty()) {
                        setState { copy(hasNextPage = false) }
                    }
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }
        )
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