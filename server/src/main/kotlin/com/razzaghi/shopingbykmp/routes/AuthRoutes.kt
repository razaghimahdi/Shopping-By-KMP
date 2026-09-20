package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.LoginRequestDTO
import com.razzaghi.shopingbykmp.business.datasource.network.splash.responses.RegisterRequestDTO
import com.razzaghi.shopingbykmp.repository.AuthRepository
import com.razzaghi.shopingbykmp.security.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.getValue


fun Route.authRoutes() {
    val authRepository by application.inject<AuthRepository>()
    val tokenManager by application.inject<TokenManager>()

    post("/login") {
        val request = call.receive<LoginRequestDTO>()
        val isValid = authRepository.validateUser(request)

        if (isValid) {
            val token = tokenManager.generateJWTToken(request.email)

            call.respond(
                HttpStatusCode.OK, MainGenericResponse(
                    result = token,
                    status = true,
                    alert = JAlertResponse(title = "Success", message = "Login successful")
                )
            )
        } else {
            call.respond(
                HttpStatusCode.Unauthorized, MainGenericResponse(
                    result = null,
                    status = false,
                    alert = JAlertResponse(title = "Error", message = "Invalid email or password")
                )
            )
        }
    }

    post("/register") {
        try {
            val request = call.receive<RegisterRequestDTO>()
            val isRegistered = authRepository.registerUser(request)

            if (isRegistered) {
                val token = tokenManager.generateJWTToken(request.email)

                call.respond(
                    HttpStatusCode.Created, MainGenericResponse(
                        result = token,
                        status = true,
                        alert = JAlertResponse(title = "Welcome", message = "Account created successfully")
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.Conflict, MainGenericResponse(
                        result = null,
                        status = false,
                        alert = JAlertResponse(title = "Error", message = "User with this email already exists")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace() // Forces the stack trace into your server terminal
            call.respondText("BACKEND CRASH: ${e.message}", status = HttpStatusCode.InternalServerError)
        }
    }
}