package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.repository.ProfileRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject
import java.io.File
import java.util.UUID

fun Route.profileRoutes() {
    val repository by application.inject<ProfileRepository>()

    authenticate("auth-jwt") {
        route("/profile") {

            // GET Profile
            get {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                    ?: return@get call.respond(HttpStatusCode.Unauthorized)

                val profile = repository.getProfile(email)
                call.respond(HttpStatusCode.OK, MainGenericResponse(result = profile, status = true))
            }

            // POST Profile (Multipart Form Data)
            post {
                val email = call.principal<JWTPrincipal>()?.payload?.getClaim("email")?.asString()
                    ?: return@post call.respond(HttpStatusCode.Unauthorized)

                val multipart = call.receiveMultipart()
                var name = ""
                var age = ""
                var imageUrl: String? = null

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            if (part.name == "name") name = part.value
                            if (part.name == "age") age = part.value
                        }
                        is PartData.FileItem -> {
                            val originalFileName = part.originalFileName ?: "image.jpg"
                            val extension = File(originalFileName).extension
                            val fileName = "${UUID.randomUUID()}.$extension" // Prevent name collisions
                            val fileBytes = part.streamProvider().readBytes()

                            // Save file locally
                            withContext(Dispatchers.IO) {
                                val uploadsDir = File("uploads")
                                if (!uploadsDir.exists()) uploadsDir.mkdirs()
                                File(uploadsDir, fileName).writeBytes(fileBytes)
                            }
                            // Construct the URL path to store in the DB
                            imageUrl = "/uploads/$fileName"
                        }
                        else -> Unit
                    }
                    part.dispose()
                }

                val success = repository.updateProfile(email, name, age, imageUrl)

                if (success) {
                    call.respond(HttpStatusCode.OK, MainGenericResponse(
                        result = true, status = true, alert = JAlertResponse("Success", "Profile updated")
                    ))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, MainGenericResponse(
                        result = false, status = false, alert = JAlertResponse("Error", "Update failed")
                    ))
                }
            }
        }
    }
}