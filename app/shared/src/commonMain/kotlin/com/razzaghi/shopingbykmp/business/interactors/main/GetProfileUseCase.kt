package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProfileDTO
import com.razzaghi.shopingbykmp.business.domain.main.toProfile
import com.razzaghi.shopingbykmp.business.domain.main.Profile

class GetProfileUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, ProfileDTO, Profile>(appDataStoreManager) {



    override suspend fun run(params: Unit, token: String) = service.getProfile(
        token = token
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<ProfileDTO>?) =
        apiResponse?.result?.toProfile()



    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}