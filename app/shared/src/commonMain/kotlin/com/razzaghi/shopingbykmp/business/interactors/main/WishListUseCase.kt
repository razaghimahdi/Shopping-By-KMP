package com.razzaghi.shopingbykmp.business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.WishlistDTO
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.toWishlist
import business.domain.main.Wishlist

class WishListUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<WishListUseCase.Params, WishlistDTO, Wishlist>(appDataStoreManager) {

    data class Params(
        val categoryId: Long?,
        val page: Int,
    )

    override suspend fun run(
        params: Params,
        token: String
    ) = service.wishlist(
        token = token,
        categoryId = if (params.categoryId != -1L) params.categoryId else null,
        page = params.page
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<WishlistDTO>?) =
        apiResponse?.result?.toWishlist()

    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true

}