package business.datasource.network.main

import business.constants.BASE_URL
import business.datasource.network.common.JRNothing
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.responses.AddressDTO
import business.datasource.network.main.responses.AddressRequestDTO
import business.datasource.network.main.responses.BasketAddRequestDTO
import business.datasource.network.main.responses.BasketDTO
import business.datasource.network.main.responses.BasketDeleteRequestDTO
import business.datasource.network.main.responses.BuyRequestDTO
import business.datasource.network.main.responses.CommentDTO
import business.datasource.network.main.responses.CommentRequestDTO
import business.datasource.network.main.responses.HomeDTO
import business.datasource.network.main.responses.NotificationDTO
import business.datasource.network.main.responses.OrderDTO
import business.datasource.network.main.responses.ProductDTO
import business.datasource.network.main.responses.ProfileDTO
import business.datasource.network.main.responses.SearchDTO
import business.datasource.network.main.responses.SearchFilterDTO
import business.datasource.network.main.responses.WishlistDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormPart
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.util.InternalAPI
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully

class MainServiceImpl(
    private val httpClient: HttpClient
) : MainService {
    override suspend fun getNotifications(token: String): MainGenericResponse<List<NotificationDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.NOTIFICATIONS
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getOrders(token: String): MainGenericResponse<List<OrderDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.ORDERS
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun buyProduct(
        token: String,
        addressId: Long,
        shippingType: Int
    ): MainGenericResponse<JRNothing> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BUY
            }
            contentType(ContentType.Application.Json)
            setBody(
                BuyRequestDTO(
                    addressId = addressId,
                    shippingType = shippingType
                )
            )
        }.body()
    }

    override suspend fun getAddresses(token: String): MainGenericResponse<List<AddressDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.ADDRESS
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun addAddress(
        token: String,
        address: String,
        city: String,
        country: String,
        state: String,
        zipCode: String
    ): MainGenericResponse<JRNothing> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.ADDRESS
            }
            contentType(ContentType.Application.Json)
            setBody(
                AddressRequestDTO(
                    address = address,
                    city = city,
                    country = country,
                    state = state,
                    zipCode = zipCode
                )
            )
        }.body()
    }

    override suspend fun getComments(
        token: String,
        id: Long
    ): MainGenericResponse<List<CommentDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.COMMENT
                encodedPath += "/$id"
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun addComment(
        token: String,
        productId: Long,
        rate: Double,
        comment: String
    ): MainGenericResponse<JRNothing> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.COMMENT
            }
            contentType(ContentType.Application.Json)
            setBody(CommentRequestDTO(comment = comment, rate = rate, productId = productId))
        }.body()
    }

    override suspend fun search(
        token: String,
        minPrice: Int?,
        maxPrice: Int?,
        sort: Int?,
        categoriesId: String?,
        page: Int
    ): MainGenericResponse<SearchDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.SEARCH

                parameter("categories_id", categoriesId)
                parameter("sort", sort)
                parameter("page", page)
                parameter("min_price", minPrice)
                parameter("max_price", maxPrice)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getSearchFilter(
        token: String,
    ): MainGenericResponse<SearchFilterDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.SEARCH_FILTER
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getProfile(token: String): MainGenericResponse<ProfileDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PROFILE
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    @OptIn(InternalAPI::class)
    override suspend fun updateProfile(
        token: String,
        name: String,
        age: String,
        image: ByteArray?
    ): MainGenericResponse<Boolean> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PROFILE
            }
            // contentType(ContentType.MultiPart.FormData)
            /*body = formData {
                append("name", name)
                if (image != null) {
                    append("image", image)
                }
                append("age", age)
            }*/

            body = MultiPartFormDataContent(
                formData {
                    append("name", name)
                    append("age", age)
                    this.append(FormPart("image", "image.jpg"))
                    this.appendInput(
                        key = "image",
                        headers = Headers.build {
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=image.jpg"
                            )
                        },
                    ) {
                        buildPacket {
                            if (image != null) {
                                writeFully(image)
                            }
                        }
                    }
                }
            )

        }.body()
    }

    override suspend fun basket(token: String): MainGenericResponse<List<BasketDTO>> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun basketAdd(
        token: String,
        id: Long,
        count: Int
    ): MainGenericResponse<JRNothing?> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET_ADD
            }
            contentType(ContentType.Application.Json)
            setBody(BasketAddRequestDTO(count = count, product = id))
        }.body()
    }

    override suspend fun basketDelete(token: String, id: Long): MainGenericResponse<JRNothing?> {
        return httpClient.post {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.BASKET_DELETE
            }
            contentType(ContentType.Application.Json)
            setBody(BasketDeleteRequestDTO(product = id))
        }.body()
    }

    override suspend fun home(token: String): MainGenericResponse<HomeDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.HOME
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun product(token: String, id: Long): MainGenericResponse<ProductDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PRODUCT
                encodedPath += "/$id"
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun like(token: String, id: Long): MainGenericResponse<JRNothing?> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.PRODUCT
                encodedPath += "/$id/"
                encodedPath += MainService.LIKE
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun wishlist(
        token: String,
        categoryId: Long?,
        page: Int
    ): MainGenericResponse<WishlistDTO> {
        return httpClient.get {
            url {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                takeFrom(BASE_URL)
                encodedPath += MainService.WISHLIST
                parameter("category_id", categoryId)
                parameter("page", page)
            }
            contentType(ContentType.Application.Json)
        }.body()
    }
}