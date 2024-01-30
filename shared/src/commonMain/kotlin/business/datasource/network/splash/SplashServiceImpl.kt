package business.datasource.network.splash

import business.constants.BASE_URL
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.splash.responses.LoginRequestDTO
import business.datasource.network.splash.responses.RegisterRequestDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

class SplashServiceImpl(
    private val httpClient: HttpClient
) : SplashService {
    override suspend fun login(email: String, password: String): MainGenericResponse<String?> {
        return httpClient.post {
            url {
                takeFrom(BASE_URL)
                encodedPath += SplashService.LOGIN
            }
            contentType(ContentType.Application.Json)
            setBody(LoginRequestDTO(email = email, password = password))
        }.body()
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): MainGenericResponse<String?> {
        return httpClient.post {
            url {
                takeFrom(BASE_URL)
                encodedPath += SplashService.REGISTER
            }
            contentType(ContentType.Application.Json)
            setBody(RegisterRequestDTO(name = name, email = email, password = password))
        }.body()
    }
}