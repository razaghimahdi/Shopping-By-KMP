package business.interactors.splash


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.BaseDataStoreUseCase
import business.core.DataState
import business.core.ProgressBarState
import business.util.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckTokenUseCase(
    private val appDataStoreManager: AppDataStore,
) : BaseDataStoreUseCase<Unit, Boolean>(appDataStoreManager) {

    override suspend fun run(params: Unit) =
        (appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: "").isNotEmpty()

    override val progressBarState = ProgressBarState.ButtonLoading

}