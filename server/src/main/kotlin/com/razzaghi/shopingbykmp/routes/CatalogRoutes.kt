package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.repository.AddressRepository
import com.razzaghi.shopingbykmp.repository.CatalogRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.catalogRoutes() {
    val repository by application.inject<CatalogRepository>()

    authenticate("auth-jwt") {

        // GET: /home
        get("/home") {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                ?: return@get call.respond(HttpStatusCode.Unauthorized)

            val homeData = repository.getHomeData(email)

            call.respond(HttpStatusCode.OK, MainGenericResponse(
                result = homeData, status = true, alert = null
            ))
        }

        // GET: /product/{id}
        get("/product/{id}") {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                ?: return@get call.respond(HttpStatusCode.Unauthorized)

            val productId = call.parameters["id"]?.toLongOrNull()

            if (productId == null) {
                call.respond(HttpStatusCode.BadRequest, MainGenericResponse(
                    result = null as Nothing?, status = false, alert = JAlertResponse("Error", "Invalid product ID")
                ))
                return@get
            }

            val product = repository.getProductDetails(email, productId)

            if (product != null) {
                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = product, status = true, alert = null
                ))
            } else {
                call.respond(HttpStatusCode.NotFound, MainGenericResponse(
                    result = null as Nothing?, status = false, alert = JAlertResponse("Error", "Product not found")
                ))
            }
        }
    }
}