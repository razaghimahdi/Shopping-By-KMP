package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.CommentDTO
import com.razzaghi.shopingbykmp.business.domain.main.toComment
import com.razzaghi.shopingbykmp.business.domain.main.Comment

class GetCommentsUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<GetCommentsUseCase.Params, List<CommentDTO>, List<Comment>>(appDataStoreManager) {

    data class Params(
        val id: Long,
    )

    override suspend fun run(params: Params, token: String) =
        service.getComments(token = token, id = params.id)

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<CommentDTO>>?) =
        apiResponse?.result?.map { it.toComment() }

    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true

}