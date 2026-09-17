package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.SearchFilterDTO
import com.razzaghi.shopingbykmp.business.domain.main.toSearchFilter
import com.razzaghi.shopingbykmp.business.domain.main.SearchFilter

class GetSearchFilterUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, SearchFilterDTO, SearchFilter>(appDataStoreManager) {


    override suspend fun run(params: Unit, token: String) = service.getSearchFilter(
        token = token,
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<SearchFilterDTO>?) =
        apiResponse?.result?.toSearchFilter()


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}