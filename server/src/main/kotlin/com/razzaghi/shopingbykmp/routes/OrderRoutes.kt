package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.repository.OrderRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.orderRoutes() {
    val repository by inject<OrderRepository>()

    authenticate("auth-jwt") {
        get("/orders") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal?.payload?.getClaim("email")?.asString()

            if (email == null) {
                call.respond(HttpStatusCode.Unauthorized, MainGenericResponse(
                    result = null as List<Nothing>?,
                    status = false,
                    alert = JAlertResponse(title = "Error", message = "Invalid Token")
                ))
                return@get
            }

            val orders = repository.getOrdersForUser(email)

            call.respond(HttpStatusCode.OK, MainGenericResponse(
                result = orders,
                status = true,
                alert = null
            ))
        }
    }
}