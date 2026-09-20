package com.razzaghi.shopingbykmp.routes

import com.razzaghi.shopingbykmp.business.datasource.network.common.JAlertResponse
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.repository.SearchRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchRoutes() {
    val repository by application.inject<SearchRepository>()

    authenticate("auth-jwt") {
        route("/search") {

            // GET: /search/filter
            get("/filter") {
                val filterData = repository.getSearchFilter()

                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = filterData, status = true, alert = null
                ))
            }

            // GET: /search
            get {
                // Extract parameters matching the MainServiceImpl.kt frontend configuration
                val minPrice = call.request.queryParameters["min_price"]?.toIntOrNull()
                val maxPrice = call.request.queryParameters["max_price"]?.toIntOrNull()
                val sort = call.request.queryParameters["sort"]?.toIntOrNull()
                val categoriesId = call.request.queryParameters["categories_id"]
                val page = call.request.queryParameters["page"]?.toIntOrNull() ?: 1

                val searchResult = repository.searchProducts(
                    minPrice = minPrice,
                    maxPrice = maxPrice,
                    sort = sort,
                    categoriesId = categoriesId,
                    page = page
                )

                call.respond(HttpStatusCode.OK, MainGenericResponse(
                    result = searchResult, status = true, alert = null
                ))
            }
        }
    }
}