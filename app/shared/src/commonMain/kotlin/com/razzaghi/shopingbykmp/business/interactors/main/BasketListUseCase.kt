package com.razzaghi.shopingbykmp.business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BasketDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.toBasket
import business.domain.main.Basket

class BasketListUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, List<BasketDTO>, List<Basket>>(appDataStoreManager) {

    override suspend fun run(params: Unit, token: String) = service.basket(token = token)

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<BasketDTO>>?) =
        apiResponse?.result?.map { it.toBasket() }


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}