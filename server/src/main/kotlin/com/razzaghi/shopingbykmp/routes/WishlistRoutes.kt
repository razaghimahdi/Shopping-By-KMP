package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.repository.WishlistRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.wishlistRoutes() {
    val repository by inject<WishlistRepository>()

    authenticate("auth-jwt") {

        // GET: /product/wishlist
        get("/product/wishlist") {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                ?: return@get call.respond(HttpStatusCode.Unauthorized)

            val categoryId = call.request.queryParameters["category_id"]?.toLongOrNull()
            val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1

            val wishlistData = repository.getWishlist(email, categoryId, page)

            call.respond(HttpStatusCode.OK, MainGenericResponse(
                result = wishlistData, status = true, alert = null
            ))
        }

        // GET: /product/{id}/like
        // Note: Your MainServiceImpl defines this as a GET request, so we match it here.
        get("/product/{id}/like") {
            val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                ?: return@get call.respond(HttpStatusCode.Unauthorized)

            val productId = call.parameters["id"]?.toLongOrNull()

            if (productId == null) {
                call.respond(HttpStatusCode.BadRequest, MainGenericResponse(
                    result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Invalid Product ID")
                ))
                return@get
            }

            val success = repository.toggleLike(email, productId)

            if (success) {
                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = JRNothing(), status = true, alert = null
                ))
            } else {
                call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                    result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Could not update wishlist")
                ))
            }
        }
    }
}