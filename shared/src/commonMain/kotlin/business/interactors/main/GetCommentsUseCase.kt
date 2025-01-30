package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.CommentDTO
import business.datasource.network.main.responses.toComment
import business.domain.main.Comment

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