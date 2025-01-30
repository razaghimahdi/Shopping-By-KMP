package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService

class AddAddressUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<AddAddressUseCase.Params, JRNothing, Boolean>(appDataStoreManager) {


    override fun mapApiResponse(apiResponse: MainGenericResponse<JRNothing>?) = apiResponse?.status

    override val progressBarType = ProgressBarState.FullScreenLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true

    data class Params(
        val address: String,
        val country: String,
        val city: String,
        val state: String,
        val zipCode: String
    )

    override suspend fun run(params: Params, token: String) = service.addAddress(
        token = token,
        address = params.address,
        city = params.city,
        state = params.state,
        zipCode = params.zipCode,
        country = params.country
    )

}