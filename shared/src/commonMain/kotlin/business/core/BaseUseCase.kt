package business.core

import business.constants.DataStoreKeys
import business.datasource.network.common.MainGenericResponse
import business.util.createException
import business.util.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<Params, ApiResponse, Result>(
    private val appDataStoreManager: AppDataStore? = null // Optional
) {

    abstract suspend fun run(params: Params, token: String): MainGenericResponse<ApiResponse>?

    abstract fun mapApiResponse(apiResponse: MainGenericResponse<ApiResponse>?): Result?

    abstract val progressBarType: ProgressBarState // Default progress bar type
    abstract val needNetworkState: Boolean  // Whether to emit network states
    abstract val createException: Boolean // Check if we need to to throw exception
    abstract val checkToken: Boolean // Check token


    fun execute(params: Params): Flow<DataState<Result>> = flow {
        try {


            // Emit loading state
            emit(DataState.Loading(progressBarType))

            // Retrieve token if AppDataStore is available
            val token = appDataStoreManager?.readValue(DataStoreKeys.TOKEN)

            if (checkToken) {
                requireNotNull(token) { "Token is required" }
            }

            // Execute core logic
            val result = run(params, token ?: "")



            if (result?.status == false && createException) {
                throw Exception(
                    result.alert?.createException()
                )
            }

            result?.alert?.let { alert ->
                if (!createException) emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }

            // Optionally emit network state
            if (needNetworkState) {
                emit(DataState.NetworkStatus(NetworkState.Good))
            }

            // Emit data
            emit(DataState.Data(data = mapApiResponse(result), status = result?.status))
        } catch (e: Exception) {
            // Optionally emit network failure state
            if (needNetworkState) {
                emit(DataState.NetworkStatus(NetworkState.Failed))
            }

            // Handle exceptions
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            // Emit idle loading state
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }

}
