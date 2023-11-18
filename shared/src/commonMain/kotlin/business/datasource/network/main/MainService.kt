package business.datasource.network.main

import business.core.KtorHttpClient
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.ProductDTO

interface MainService {
    companion object {
        const val HOME = "home"
        const val PRODUCT = "product"
    }

    suspend fun home(token: String): MainGenericResponse<HomeDTO>
    suspend fun product(token: String, id: Int): MainGenericResponse<ProductDTO>


    object Factory {
        fun build(): MainService {
            return MainServiceImpl(
                httpClient = KtorHttpClient.httpClient()
            )
        }
    }


}