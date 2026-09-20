package com.razzaghi.shopingbykmp.business.datasource.network.main

import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.AddressDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.BasketDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.HomeDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.NotificationDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.OrderDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProductDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.ProfileDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.SearchDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.SearchFilterDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.WishlistDTO
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse

interface MainService {
    companion object {
        const val SEARCH_FILTER = "search/filter"
        const val SEARCH = "search"
        const val BASKET = "basket"
        const val BUY = "basket/buy"
        const val BASKET_ADD = "basket/add"
        const val BASKET_DELETE = "basket/delete"
        const val HOME = "home"
        const val ORDERS = "orders"
        const val PRODUCT = "product"
        const val LIKE = "like"
        const val PROFILE = "profile"
        const val COMMENT = "comment"
        const val WISHLIST = "product/wishlist"
        const val ADDRESS = "address"
        const val NOTIFICATIONS = "notifications"
    }

    suspend fun getNotifications(
        token: String,
    ): MainGenericResponse<List<NotificationDTO>>

    suspend fun getOrders(
        token: String,
    ): MainGenericResponse<List<OrderDTO>>

    suspend fun buyProduct(
        token: String,
        addressId: Long,
        shippingType: Int,
    ): MainGenericResponse<JRNothing>

    suspend fun getAddresses(
        token: String,
    ): MainGenericResponse<List<AddressDTO>>

    suspend fun addAddress(
        token: String,
        address: String,
        city: String,
        country: String,
        state: String,
        zipCode: String,
    ): MainGenericResponse<JRNothing>

    suspend fun getComments(
        token: String,
        id: Long,
    ): MainGenericResponse<List<CommentDTO>>

    suspend fun addComment(
        token: String,
        productId: Long,
        rate: Double,
        comment: String,
    ): MainGenericResponse<JRNothing>

    suspend fun search(
        token: String,
        minPrice: Int?,
        maxPrice: Int?,
        sort: Int?,
        categoriesId: String?,
        page: Int,
    ): MainGenericResponse<SearchDTO>

    suspend fun getSearchFilter(
        token: String,
    ): MainGenericResponse<SearchFilterDTO>

    suspend fun getProfile(token: String): MainGenericResponse<ProfileDTO>
    suspend fun updateProfile(
        token: String,
        name: String,
        age: String,
        image: ByteArray?,
    ): MainGenericResponse<Boolean>

    suspend fun basket(token: String): MainGenericResponse<List<BasketDTO>>
    suspend fun basketAdd(token: String, id: Long, count: Int): MainGenericResponse<JRNothing?>
    suspend fun basketDelete(token: String, id: Long): MainGenericResponse<JRNothing?>
    suspend fun home(token: String): MainGenericResponse<HomeDTO>
    suspend fun product(token: String, id: Long): MainGenericResponse<ProductDTO>
    suspend fun like(token: String, id: Long): MainGenericResponse<JRNothing?>
    suspend fun wishlist(
        token: String,
        categoryId: Long?,
        page: Int
    ): MainGenericResponse<WishlistDTO>




}