package com.razzaghi.interactor.main


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import com.razzaghi.datasource.network.main.MainService
import business.core.ProgressBarState
import business.core.UIComponent
import com.razzaghi.interactor.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateProfileInteractor(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(
        name: String,
        age: String,
        image: ByteArray?,
    ): Flow<DataState<Boolean>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))

            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""


            val apiResponse =
                service.updateProfile(token = token, name = name, age = age, image = image)



            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }



            emit(DataState.Data( apiResponse.status))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}