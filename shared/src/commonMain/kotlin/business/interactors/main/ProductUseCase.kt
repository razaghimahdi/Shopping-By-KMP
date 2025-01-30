package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.toProduct
import business.domain.main.Product

class ProductUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<ProductUseCase.Params, ProductDTO, Product>(appDataStoreManager) {

    data class Params(
        val id: Long,
    )

    override suspend fun run(params: Params, token: String) =
        service.product(token = token, id = params.id)

    override fun mapApiResponse(apiResponse: MainGenericResponse<ProductDTO>?) =
        apiResponse?.result?.toProduct()

    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true
}