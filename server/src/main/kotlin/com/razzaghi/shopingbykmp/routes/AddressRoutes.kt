package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressRequestDTO
import com.razzaghi.shopingbykmp.repository.AddressRepository
import com.razzaghi.shopingbykmp.repository.AuthRepository
import com.razzaghi.shopingbykmp.security.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.addressRoutes() {
    val repository by application.inject<AddressRepository>()

    authenticate("auth-jwt") {
        route("/address") {

            // GET: Fetch Addresses
            get {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString() ?: return@get call.respond(
                    HttpStatusCode.Unauthorized,
                    MainGenericResponse(result = null as List<Nothing>?, status = false, alert = JAlertResponse("Error", "Invalid Token"))
                )

                val addresses = repository.getAddresses(email)
                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = addresses,
                    status = true,
                    alert = null
                ))
            }

            // POST: Add new Address
            post {
                val principal = call.principal<JWTPrincipal>()
                val email = principal?.payload?.getClaim("email")?.asString() ?: return@post call.respond(
                    HttpStatusCode.Unauthorized,
                    MainGenericResponse(result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Invalid Token"))
                )

                val request = call.receive<AddressRequestDTO>()
                val success = repository.addAddress(email, request)

                if (success) {
                    call.respond(HttpStatusCode.OK, MainGenericResponse(
                        result = JRNothing(),
                        status = true,
                        alert = JAlertResponse("Success", "Address added successfully")
                    ))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                        result = null as JRNothing?,
                        status = false,
                        alert = JAlertResponse("Error", "Failed to add address")
                    ))
                }
            }
        }
    }
}