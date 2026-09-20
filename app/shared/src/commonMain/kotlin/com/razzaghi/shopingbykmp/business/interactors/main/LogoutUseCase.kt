package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.constants.DataStoreKeys
import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseDataStoreUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState

class LogoutUseCase(
    private val appDataStoreManager: AppDataStore,
) : BaseDataStoreUseCase<Unit, Boolean>(appDataStoreManager) {

    override suspend fun run(params: Unit): Boolean {
        appDataStoreManager.setValue(
            DataStoreKeys.TOKEN,
            ""
        )
        return true
    }

    override val progressBarState = ProgressBarState.ButtonLoading


}