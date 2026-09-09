package business.core

import business.util.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseDataStoreUseCase<Params, Result>(
    private val appDataStoreManager: AppDataStore
) {

    abstract suspend fun run(params: Params): Result

    abstract val progressBarState: ProgressBarState

    fun execute(params: Params): Flow<DataState<Result>> = flow {
        try {

            // Emit loading state
            emit(DataState.Loading(progressBarState))

            // Execute local cache retrieval
            val result = run(params)

            // Emit retrieved data
            emit(DataState.Data(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            // Emit idle state
            emit(DataState.Loading(ProgressBarState.Idle))
        }
    }

}
