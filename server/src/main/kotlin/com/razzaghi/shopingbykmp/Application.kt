package com.razzaghi.shopingbykmp

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.LoginRequestDTO
import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.RegisterRequestDTO
import com.razzaghi.shopingbykmp.database.DatabaseFactory
import com.razzaghi.shopingbykmp.di.appModule
import com.razzaghi.shopingbykmp.repository.AuthRepository
import com.razzaghi.shopingbykmp.routes.addressRoutes
import com.razzaghi.shopingbykmp.routes.authRoutes
import com.razzaghi.shopingbykmp.routes.basketRoutes
import com.razzaghi.shopingbykmp.routes.catalogRoutes
import com.razzaghi.shopingbykmp.routes.commentRoutes
import com.razzaghi.shopingbykmp.routes.notificationRoutes
import com.razzaghi.shopingbykmp.routes.orderRoutes
import com.razzaghi.shopingbykmp.routes.profileRoutes
import com.razzaghi.shopingbykmp.routes.searchRoutes
import com.razzaghi.shopingbykmp.routes.wishlistRoutes
import com.razzaghi.shopingbykmp.security.TokenManager
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpMethod
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.request.receive
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    DatabaseFactory.init()


    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        anyHost() // Note: restrict this in production
    }

    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    val tokenManager by inject<TokenManager>()

    install(Authentication) {
        jwt("auth-jwt") {
            realm = "Shopping-By-KMP Server"
            verifier(
                com.auth0.jwt.JWT
                    .require(com.auth0.jwt.algorithms.Algorithm.HMAC256(tokenManager.secret))
                    .withAudience(tokenManager.audience)
                    .withIssuer(tokenManager.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("email").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }

    routing {
        get("/") {
            call.respondText("Shopping-By-KMP API is running!")
        }

        authRoutes()
        notificationRoutes()
        orderRoutes()
        addressRoutes()
        profileRoutes()
        basketRoutes()
        catalogRoutes()
        searchRoutes()
        wishlistRoutes()
        commentRoutes()
    }

}