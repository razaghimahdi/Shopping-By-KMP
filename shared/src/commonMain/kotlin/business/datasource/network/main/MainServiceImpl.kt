package business.datasource.network.main

import business.constants.BASE_URL
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.WishlistDTO
import business.datasource.network.splash.SplashService
import business.datasource.network.splash.responses.RegisterRequestDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.parameters
import io.ktor.http.takeFrom
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

class MainServiceImpl(
    private val httpClient: HttpClient
) : MainService {
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