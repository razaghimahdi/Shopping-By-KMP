package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService

class AddBasketUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<AddBasketUseCase.Params, JRNothing?, Boolean>(appDataStoreManager) {


    override fun mapApiResponse(apiResponse: MainGenericResponse<JRNothing?>?) = apiResponse?.status


    override val progressBarType = ProgressBarState.FullScreenLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true


    data class Params(
        val id: Long,
        val count: Int
    )


    override suspend fun run(params: Params, token: String): MainGenericResponse<JRNothing?> =
        service.basketAdd(token = token, id = params.id, count = params.count)

}