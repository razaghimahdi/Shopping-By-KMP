package business.interactors.main


import business.constants.AUTHORIZATION_BEARER_TOKEN
import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import business.core.NetworkState
import business.core.ProgressBarState
import business.core.UIComponent
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.toHome
import business.datasource.network.main.responses.toSearch
import business.datasource.network.main.responses.toWishlist
import business.util.handleUseCaseException
import business.datasource.network.splash.SplashService
import business.domain.main.Category
import business.domain.main.Home
import business.domain.main.Search
import business.domain.main.Wishlist
import business.util.Logger
import business.util.createException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchInteractor(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(
        minPrice: Int? = null,
        maxPrice: Int? = null,
        categories: List<Category>? = null,
        page: Int
    ): Flow<DataState<Search>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))

            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""

            val apiResponse = service.search(
                token = token,
                minPrice = minPrice,
                maxPrice = maxPrice,
                categoriesId = categories?.map { it.id }?.joinToString(","),
                page = page
            )


            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response<Search>(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }

            val result = apiResponse.result?.toSearch()


            emit(DataState.Data(result))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}