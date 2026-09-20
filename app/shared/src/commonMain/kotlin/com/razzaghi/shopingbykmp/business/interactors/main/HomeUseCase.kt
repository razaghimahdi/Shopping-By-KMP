package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.HomeDTO
import com.razzaghi.shopingbykmp.business.domain.main.toHome
import com.razzaghi.shopingbykmp.business.domain.main.Home

class HomeUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, HomeDTO, Home>(appDataStoreManager) {


    override suspend fun run(params: Unit, token: String)=service.home(token = token)

    override fun mapApiResponse(apiResponse: MainGenericResponse<HomeDTO>?)=apiResponse?.result?.toHome()


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}