package com.razzaghi.interactor.main


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import business.core.NetworkState
import com.razzaghi.datasource.network.main.MainService
import business.core.ProgressBarState
import business.domain.main.Product
import com.razzaghi.datasource.network.main.responses.toProduct
import com.razzaghi.interactor.createException
import com.razzaghi.interactor.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductInteractor(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(id: Int): Flow<DataState<Product>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.LoadingWithLogo))

            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""


            val apiResponse = service.product(token = token, id = id)



            if (apiResponse.status == false || apiResponse.result == null) {
                throw Exception(
                    apiResponse.alert?.createException()
                )
            }


            val result = apiResponse.result?.toProduct()


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