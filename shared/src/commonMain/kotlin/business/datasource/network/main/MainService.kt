package business.datasource.network.main

import business.core.KtorHttpClient
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.BasketDTO
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.WishlistDTO

interface MainService {
    companion object {
        const val BASKET = "basket"
        const val BASKET_ADD = "basket/add"
        const val BASKET_DELETE = "basket/delete"
        const val HOME = "home"
        const val PRODUCT = "product"
        const val LIKE = "like"
        const val WISHLIST = "product/wishlist"
    }

    suspend fun basket(token: String): MainGenericResponse<List<BasketDTO>>
    suspend fun basketAdd(token: String, id: Int, count: Int): MainGenericResponse<JRNothing?>
    suspend fun basketDelete(token: String, id: Int): MainGenericResponse<JRNothing?>
    suspend fun home(token: String): MainGenericResponse<HomeDTO>
    suspend fun product(token: String, id: Int): MainGenericResponse<ProductDTO>
    suspend fun like(token: String, id: Int): MainGenericResponse<JRNothing?>
    suspend fun wishlist(
        token: String,
        categoryId: Int?,
        page: Int
    ): MainGenericResponse<WishlistDTO>


    object Factory {
        fun build(): MainService {
            return MainServiceImpl(
                httpClient = KtorHttpClient.httpClient()
            )
        }
    }


}