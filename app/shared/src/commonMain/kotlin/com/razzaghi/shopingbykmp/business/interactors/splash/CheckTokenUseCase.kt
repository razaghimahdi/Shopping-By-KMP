package com.razzaghi.shopingbykmp.business.interactors.splash


import com.razzaghi.shopingbykmp.business.constants.DataStoreKeys
import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseDataStoreUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState

class CheckTokenUseCase(
    private val appDataStoreManager: AppDataStore,
) : BaseDataStoreUseCase<Unit, Boolean>(appDataStoreManager) {

    override suspend fun run(params: Unit) =
        (appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: "").isNotEmpty()

    override val progressBarState = ProgressBarState.ButtonLoading

}