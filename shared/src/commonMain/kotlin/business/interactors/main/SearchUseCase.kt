package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.SearchDTO
import business.datasource.network.main.responses.toSearch
import business.domain.main.Category
import business.domain.main.Search

class SearchUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<SearchUseCase.Params, SearchDTO, Search>(appDataStoreManager) {


    data class Params(
        val minPrice: Int? = null,
        val maxPrice: Int? = null,
        val categories: List<Category>? = null,
        val sort: Int?,
        val page: Int,
    )

    override suspend fun run(
        params: Params,
        token: String
    ) = service.search(
        token = token,
        minPrice = params.minPrice,
        maxPrice = params.maxPrice,
        sort = params.sort,
        categoriesId = params.categories?.map { it.id }?.joinToString(","),
        page = params.page
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<SearchDTO>?) =
        apiResponse?.result?.toSearch()

    override val progressBarType = ProgressBarState.ScreenLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true
}