package com.razzaghi.interactor.main


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import business.core.NetworkState
import business.core.ProgressBarState
import business.core.UIComponent
import business.domain.main.Wishlist
import com.razzaghi.datasource.network.main.MainService
import com.razzaghi.datasource.network.main.responses.toWishlist
import com.razzaghi.interactor.createException
import com.razzaghi.interactor.handleUseCaseException
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
                categoryId = if (categoryId != -1) categoryId else null,
                page = page
            )



            if (apiResponse.status == false) {
                throw Exception(
                    apiResponse.alert?.createException()
                )
            }


            val result = apiResponse.result?.toWishlist()


            emit(DataState.NetworkStatus(NetworkState.Good))
            emit(DataState.Data(result))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.NetworkStatus(NetworkState.Failed))
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}