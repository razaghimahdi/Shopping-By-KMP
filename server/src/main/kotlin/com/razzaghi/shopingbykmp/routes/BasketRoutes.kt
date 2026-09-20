package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BasketAddRequestDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BasketDeleteRequestDTO
import com.razzaghi.shopingbykmp.repository.AddressRepository
import com.razzaghi.shopingbykmp.repository.AuthRepository
import com.razzaghi.shopingbykmp.repository.BasketRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.basketRoutes() {
    val repository by application.inject<BasketRepository>()

    authenticate("auth-jwt") {
        route("/basket") {

            // GET: Fetch Cart Items
            get {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                val basket = repository.getBasket(email)

                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = basket, status = true, alert = null
                ))
            }

            // POST: Add to Cart
            post("/add") {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                val request = call.receive<BasketAddRequestDTO>()

                val success = repository.addToBasket(email, request)

                if (success) {
                    call.respond(HttpStatusCode.OK, MainGenericResponse(
                        result = JRNothing(), status = true, alert = JAlertResponse("Success", "Added to basket")
                    ))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                        result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Could not add to basket")
                    ))
                }
            }

            // POST: Delete from Cart
            post("/delete") {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                val request = call.receive<BasketDeleteRequestDTO>()

                val success = repository.deleteFromBasket(email, request)

                if (success) {
                    call.respond(HttpStatusCode.OK, MainGenericResponse(
                        result = JRNothing(), status = true, alert = JAlertResponse("Success", "Removed from basket")
                    ))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                        result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Could not remove item")
                    ))
                }
            }
        }
    }
}