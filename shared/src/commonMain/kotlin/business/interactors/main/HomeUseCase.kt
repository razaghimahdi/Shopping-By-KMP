package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.toHome
import business.domain.main.Home

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