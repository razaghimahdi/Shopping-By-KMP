package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService

class LikeUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<LikeUseCase.Params, JRNothing?, Boolean>(appDataStoreManager) {

    data class Params(
        val id: Long,
    )

    override suspend fun run(params: Params, token: String) =
        service.like(token = token, id = params.id)

    override fun mapApiResponse(apiResponse: MainGenericResponse<JRNothing?>?) = apiResponse?.status

    override val progressBarType = ProgressBarState.Idle
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true

}