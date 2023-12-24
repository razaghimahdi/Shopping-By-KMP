package business.datasource.network.main

import business.constants.BASE_URL
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.BasketAddRequestDTO
import business.datasource.network.main.responses.BasketDTO
import business.datasource.network.main.responses.BasketDeleteRequestDTO
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.ProfileDTO
import business.datasource.network.main.responses.WishlistDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

class MainServiceImpl(
    private val httpClient: HttpClient
) : MainService {

    override suspend fun getProfile(token: String): MainGenericResponse<ProfileDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PROFILE
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun basket(token: String): MainGenericResponse<List<BasketDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun basketAdd(
        token: String,
        id: Int,
        count: Int
    ): MainGenericResponse<JRNothing?> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET_ADD
            }
            contentType(ContentType.Application.Json)
            setBody(BasketAddRequestDTO(count = id, product = id))
        }.body()
    }

    override suspend fun basketDelete(token: String, id: Int): MainGenericResponse<JRNothing?> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET_DELETE
            }
            contentType(ContentType.Application.Json)
            setBody(BasketDeleteRequestDTO(product = id))
        }.body()
    }

    override suspend fun home(token: String): MainGenericResponse<HomeDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.HOME
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun product(token: String, id: Int): MainGenericResponse<ProductDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PRODUCT
                encodedPath += "/$id"
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun like(token: String, id: Int): MainGenericResponse<JRNothing?> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PRODUCT
                encodedPath += "/$id/"
                encodedPath += MainService.LIKE
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun wishlist(
        token: String,
        categoryId: Int?,
        page: Int
    ): MainGenericResponse<WishlistDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.WISHLIST
                parameter("category_id", categoryId)
                parameter("page", page)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}