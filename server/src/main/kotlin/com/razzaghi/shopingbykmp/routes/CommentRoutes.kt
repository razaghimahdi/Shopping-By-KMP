package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentRequestDTO
import com.razzaghi.shopingbykmp.repository.CommentRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.commentRoutes() {
    val repository by inject<CommentRepository>()

    authenticate("auth-jwt") {
        route("/comment") {

            // GET: /comment/{id}
            get("/{id}") {
                val productId = call.parameters["id"]?.toLongOrNull()

                if (productId == null) {
                    call.respond(HttpStatusCode.BadRequest, MainGenericResponse(
                        result = null as List<Nothing>?, status = false, alert = JAlertResponse("Error", "Invalid Product ID")
                    ))
                    return@get
                }

                val comments = repository.getComments(productId)

                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = comments, status = true, alert = null
                ))
            }

            // POST: /comment
            post {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                    ?: return@post call.respond(HttpStatusCode.Unauthorized)

                val request = call.receive<CommentRequestDTO>()
                val success = repository.addComment(email, request)

                if (success) {
                    call.respond(HttpStatusCode.OK, MainGenericResponse(
                        result = JRNothing(), status = true, alert = JAlertResponse("Success", "Comment posted successfully")
                    ))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                        result = null as JRNothing?, status = false, alert = JAlertResponse("Error", "Failed to post comment")
                    ))
                }
            }
        }
    }
}