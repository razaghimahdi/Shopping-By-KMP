package business.interactors.main


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.BaseDataStoreUseCase
import business.core.ProgressBarState

class GetEmailFromCacheUseCase(
    private val appDataStoreManager: AppDataStore,
) : BaseDataStoreUseCase<Unit, String>(appDataStoreManager) {


    override suspend fun run(params: Unit) =
        appDataStoreManager.readValue(DataStoreKeys.EMAIL) ?: ""

    override val progressBarState = ProgressBarState.LoadingWithLogo


}