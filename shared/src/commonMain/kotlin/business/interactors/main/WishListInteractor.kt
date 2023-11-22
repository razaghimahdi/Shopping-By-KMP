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
import business.datasource.network.main.responses.toWishlist
import business.util.handleUseCaseException
import business.datasource.network.splash.SplashService
import business.domain.main.Home
import business.domain.main.Wishlist
import business.util.Logger
import business.util.createException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WishListInteractor(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(categoryId: Int?, page: Int): Flow<DataState<Wishlist>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.LoadingWithLogo))

            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""


            val apiResponse = service.wishlist(
                token = token,
                categoryId =if(categoryId != -1) categoryId else null,
                page = page
            )



            if (apiResponse.status == false) {
                throw Exception(
                    apiResponse.alert?.createException()
                )
            }


            val result = apiResponse.result?.toWishlist()


            emit(DataState.NetworkStatus<Wishlist>(NetworkState.Good))
            emit(DataState.Data(result))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.NetworkStatus<Wishlist>(NetworkState.Failed))
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}