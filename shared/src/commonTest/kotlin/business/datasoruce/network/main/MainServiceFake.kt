package business.datasoruce.network.main

import business.constants.BASE_URL
import business.datasource.network.main.MainService
import business.datasource.network.main.MainServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ConnectTimeoutException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MainServiceFake {


    companion object Factory {

        private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
        private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

        fun build(type: MainServiceResponseType): MainService {
            val client = HttpClient(MockEngine) {
                expectSuccess = false
                install(ContentNegotiation) {
                    json(Json {
                        explicitNulls = false
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                        encodeDefaults = true
                        classDiscriminator = "#class"
                    })
                }

                engine {
                    addHandler { request ->
                        when (request.url.fullUrl) {
                            "$BASE_URL${MainService.HOME}" -> {
                                this.homeRespond(type = type, request = request)
                            }

                            else -> error("Unhandled ${request.url.fullUrl}")
                        }
                    }
                }
            }

            return MainServiceImpl(client)
        }

        private fun MockRequestHandleScope.homeRespond(
            type: MainServiceResponseType,
            request: HttpRequestData
        ): HttpResponseData {

            val responseHeaders = headersOf(
                "Content-Type" to listOf("application/json", "charset=utf-8"),
                HttpHeaders.Authorization to listOf("BREAR Token")
            )
            return when (type) {
                is MainServiceResponseType.Empty -> {
                    respond(
                        HomeFakeDataGenerator.empty,
                        status = HttpStatusCode.OK,
                        headers = responseHeaders,
                    )
                }
                is MainServiceResponseType.UnauthenticatedData -> {
                    respond(
                        HomeFakeDataGenerator.unauthenticatedData,
                        status = HttpStatusCode.Unauthorized,
                        headers = responseHeaders,
                    )
                }

                is MainServiceResponseType.MalformedData -> {
                    respond(
                        HomeFakeDataGenerator.malformedData,
                        status = HttpStatusCode.OK,
                        headers = responseHeaders
                    )
                }

                is MainServiceResponseType.GoodData -> {
                    respond(
                        HomeFakeDataGenerator.goodData,
                        status = HttpStatusCode.OK,
                        headers = responseHeaders
                    )
                }

                is MainServiceResponseType.TimeOut -> {
                    throw ConnectTimeoutException(request)
                }
            }
        }


    }


}