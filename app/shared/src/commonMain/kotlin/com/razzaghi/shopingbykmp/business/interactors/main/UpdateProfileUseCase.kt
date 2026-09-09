package com.razzaghi.shopingbykmp.business.interactors.main


import androidx.compose.ui.graphics.ImageBitmap
import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import common.toBytes

class UpdateProfileUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<UpdateProfileUseCase.Params, Boolean, Boolean>(appDataStoreManager) {

    data class Params(
        val name: String,
        val age: String,
        val image: ImageBitmap?,
    )

    override suspend fun run(
        params: Params,
        token: String
    ) = service.updateProfile(
        token = token,
        name = params.name,
        age = params.age,
        image = params.image?.toBytes()
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<Boolean>?) = apiResponse?.status

    override val progressBarType = ProgressBarState.ButtonLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true
}