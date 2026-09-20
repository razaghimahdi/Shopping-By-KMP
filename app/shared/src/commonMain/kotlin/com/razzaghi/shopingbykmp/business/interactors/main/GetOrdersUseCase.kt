package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.OrderDTO
import com.razzaghi.shopingbykmp.business.domain.main.toOrder
import com.razzaghi.shopingbykmp.business.domain.main.Order

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