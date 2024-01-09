package business.datasoruce.network.splash

import business.constants.BASE_URL
import business.datasource.network.splash.SplashService
import business.datasource.network.splash.SplashServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ConnectTimeoutException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SplashServiceFake {



    companion object Factory {

        private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
        private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"

        fun build(type: LoginServiceResponseType): SplashService {
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
                            "$BASE_URL${SplashService.LOGIN}" -> {
                                val responseHeaders = headersOf(
                                    "Content-Type" to listOf("application/json", "charset=utf-8")
                                )
                                when (type) {
                                    is LoginServiceResponseType.Empty -> {
                                        respond(
                                            LoginFakeDataGenerator.empty,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders,
                                        )
                                    }
                                    is LoginServiceResponseType.MalformedData -> {
                                        respond(
                                            LoginFakeDataGenerator.malformedData,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is LoginServiceResponseType.FillDataCurrently -> {
                                        respond(
                                            LoginFakeDataGenerator.fillDataCurrently,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is LoginServiceResponseType.GoodData -> {
                                        respond(
                                            LoginFakeDataGenerator.goodData,
                                            status = HttpStatusCode.OK,
                                            headers = responseHeaders
                                        )
                                    }
                                    is LoginServiceResponseType.TimeOut -> {
                                        throw ConnectTimeoutException(request)
                                    }
                                }
                            }
                            else -> error("Unhandled ${request.url.fullUrl}")
                        }
                    }
                }
            }

            return SplashServiceImpl(client)
        }


    }


}