package com.razzaghi.interactor.splash


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import business.core.ProgressBarState
import com.razzaghi.interactor.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckTokenInteractor(
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(): Flow<DataState<Boolean>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))


            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""




            val isTokenValid = token.isNotEmpty()


            emit(DataState.Data(isTokenValid))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}