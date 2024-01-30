package presentation.ui.main.wishlist.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Category
import business.domain.main.category_all
import business.interactors.main.LikeInteractor
import business.interactors.main.WishListInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class WishlistViewModel(
    private val wishListInteractor: WishListInteractor,
    private val likeInteractor: LikeInteractor,
) : ViewModel() {




    val state: MutableState<WishlistState> = mutableStateOf(WishlistState())


    fun onTriggerEvent(event: WishlistEvent) {
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

            is WishlistEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is WishlistEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
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

    private fun likeProduct(id: Int) {
        likeInteractor.execute(id = id)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        onTriggerEvent(WishlistEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            if (it) {
                                val currentList = state.value.wishlist.products.toMutableList()
                                val item = currentList.find {product->
                                    product.id == id }
                                currentList.remove(item)
                                state.value =
                                    state.value.copy(wishlist = state.value.wishlist.copy(products = currentList))
                            }
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
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
                        onTriggerEvent(WishlistEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {

                            val categories = it.categories.toMutableList()
                            categories.add(0, category_all)

                            state.value =
                                state.value.copy(wishlist = it.copy(categories = categories))
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun getNextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        wishListInteractor.execute(categoryId = state.value.categoryId, page = state.value.page)
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        onTriggerEvent(WishlistEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value =
                                state.value.copy(wishlist = state.value.wishlist.copy(products = it.products))
                            if (it.products.isEmpty()) {
                                state.value = state.value.copy(hasNextPage = false)
                            }
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectedCategory(category: Category) {
        state.value = state.value.copy(selectedCategory = category)
        getWishlist()
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getWishlist()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}