package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.OrderDTO
import business.datasource.network.main.responses.toOrder
import business.domain.main.Order

class GetOrdersUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, List<OrderDTO>, List<Order>>(appDataStoreManager) {


    override suspend fun run(params: Unit, token: String) = service.getOrders(token = token)

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<OrderDTO>>?) =
        apiResponse?.result?.map { it.toOrder() }


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}