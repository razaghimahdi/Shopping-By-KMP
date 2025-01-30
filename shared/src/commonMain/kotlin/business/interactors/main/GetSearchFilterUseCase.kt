package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.SearchFilterDTO
import business.datasource.network.main.responses.toSearchFilter
import business.domain.main.SearchFilter

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