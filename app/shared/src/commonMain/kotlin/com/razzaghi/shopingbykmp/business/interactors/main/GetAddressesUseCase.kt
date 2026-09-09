package com.razzaghi.shopingbykmp.business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.toAddress
import com.razzaghi.shopingbykmp.business.domain.main.Address

class GetAddressesUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, List<AddressDTO>, List<Address>>(appDataStoreManager) {

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<AddressDTO>>?) =
        apiResponse?.result?.map { it.toAddress() }


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true

    override suspend fun run(
        params: Unit,
        token: String
    ): MainGenericResponse<List<AddressDTO>> {
        return service.getAddresses(token = token)
    }


}