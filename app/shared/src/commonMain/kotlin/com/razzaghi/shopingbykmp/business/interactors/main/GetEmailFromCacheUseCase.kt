package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.constants.DataStoreKeys
import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseDataStoreUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState

class GetEmailFromCacheUseCase(
    private val appDataStoreManager: AppDataStore,
) : BaseDataStoreUseCase<Unit, String>(appDataStoreManager) {


    override suspend fun run(params: Unit) =
        appDataStoreManager.readValue(DataStoreKeys.EMAIL) ?: ""

    override val progressBarState = ProgressBarState.LoadingWithLogo


}