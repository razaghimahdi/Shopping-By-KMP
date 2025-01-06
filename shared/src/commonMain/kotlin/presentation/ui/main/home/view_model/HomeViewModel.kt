package presentation.ui.main.home.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.HomeInteractor
import business.interactors.main.LikeInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val homeInteractor: HomeInteractor,
    private val likeInteractor: LikeInteractor,
) : BaseViewModel<HomeEvent, HomeState, Nothing>() {

    override fun setInitialState() = HomeState()


    override fun onTriggerEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.Like -> {
                likeProduct(id = event.id)
            }

            is HomeEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is HomeEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getHome()
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
                            if (it) updateLike(id)
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun updateLike(id: Long) {

        updateMostSaleProductLike(id = id)
        updateNewestProductLike(id = id)
        updateFlashSaleProductLike(id = id)

    }

    private fun updateFlashSaleProductLike(id: Long) {

        val tmpFlashSale = state.value.home.flashSale.products.toMutableList()
        var currentItemFlashSale = tmpFlashSale.find { it.id == id }
        val indexCurrentItemFlashSale = tmpFlashSale.indexOf(currentItemFlashSale)
        val newLikes3 =
            if (currentItemFlashSale?.isLike == true) currentItemFlashSale.likes.minus(1) else currentItemFlashSale?.likes?.plus(
                1
            )
        currentItemFlashSale = currentItemFlashSale?.copy(
            isLike = !currentItemFlashSale.isLike,
            likes = newLikes3 ?: 0
        )
        if (currentItemFlashSale != null) {
            tmpFlashSale[indexCurrentItemFlashSale] = currentItemFlashSale
        }

        setState {
            copy(
                home = state.value.home.copy(
                    flashSale = state.value.home.flashSale.copy(
                        products = tmpFlashSale
                    )
                )
            )
        }

    }

    private fun updateNewestProductLike(id: Long) {
        val tmpNewestProduct = state.value.home.newestProduct.toMutableList()
        var currentItemNewestProduct = tmpNewestProduct.find { it.id == id }
        val indexCurrentItemNewestProduct = tmpNewestProduct.indexOf(currentItemNewestProduct)

        val newLikes2 =
            if (currentItemNewestProduct?.isLike == true) currentItemNewestProduct.likes.minus(1) else currentItemNewestProduct?.likes?.plus(
                1
            )

        currentItemNewestProduct = currentItemNewestProduct?.copy(
            isLike = !currentItemNewestProduct.isLike,
            likes = newLikes2 ?: 0
        )


        if (currentItemNewestProduct != null) {
            tmpNewestProduct[indexCurrentItemNewestProduct] = currentItemNewestProduct
        }

        setState { copy(home = state.value.home.copy(newestProduct = tmpNewestProduct)) }

    }

    private fun updateMostSaleProductLike(id: Long) {
        val tmpMostSale = state.value.home.mostSale.toMutableList()
        var currentItemMostSale = tmpMostSale.find { it.id == id }
        val indexCurrentItemMostSale = tmpMostSale.indexOf(currentItemMostSale)

        val newLikes1 =
            if (currentItemMostSale?.isLike == true) currentItemMostSale.likes.minus(1) else currentItemMostSale?.likes?.plus(
                1
            )

        currentItemMostSale =
            currentItemMostSale?.copy(isLike = !currentItemMostSale.isLike, likes = newLikes1 ?: 0)

        if (currentItemMostSale != null) {
            tmpMostSale[indexCurrentItemMostSale] = currentItemMostSale
        }

        setState { copy(home = state.value.home.copy(mostSale = tmpMostSale)) }
    }


    private fun getHome() {
        homeInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(HomeEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        val currentDateTime =
                            Instant.parse(it.flashSale.expiredAt).toLocalDateTime(TimeZone.UTC)
                        setState { copy(home = it, time = currentDateTime) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getHome()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}