package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.JRNothing
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService

class AddCommentUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<AddCommentUseCase.Params, JRNothing, Boolean>(appDataStoreManager) {

    data class Params(
        val productId: Long,
        val rate: Double,
        val comment: String,
    )

    override suspend fun run(params: Params, token: String) = service.addComment(
        token = token,
        productId = params.productId,
        rate = params.rate,
        comment = params.comment,
    )

    override fun mapApiResponse(apiResponse: MainGenericResponse<JRNothing>?) = apiResponse?.status

    override val progressBarType = ProgressBarState.FullScreenLoading
    override val needNetworkState = false
    override val createException = false
    override val checkToken = true


}