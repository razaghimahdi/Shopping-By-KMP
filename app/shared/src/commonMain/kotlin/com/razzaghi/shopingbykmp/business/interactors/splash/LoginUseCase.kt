package com.razzaghi.shopingbykmp.business.interactors.splash


import com.razzaghi.shopingbykmp.business.constants.AUTHORIZATION_BEARER_TOKEN
import com.razzaghi.shopingbykmp.business.constants.DataStoreKeys
import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.splash.SplashService

class LoginUseCase(
    private val service: SplashService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<LoginUseCase.Params, String?, String>(appDataStoreManager) {

    data class Params(
        val email: String,
        val password: String,
    )

    override suspend fun run(params: Params, token: String): MainGenericResponse<String?> {
        val apiResponse = service.login(params.email, params.password)

        val result = apiResponse.result


        if (result != null) {
            appDataStoreManager.setValue(
                DataStoreKeys.TOKEN,
                AUTHORIZATION_BEARER_TOKEN + result
            )
            appDataStoreManager.setValue(
                DataStoreKeys.EMAIL,
                params.email
            )
        }
        return apiResponse
    }

    override fun mapApiResponse(apiResponse: MainGenericResponse<String?>?) = apiResponse?.result

    override val progressBarType = ProgressBarState.ButtonLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = false

}