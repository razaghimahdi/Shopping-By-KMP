package business.interactors.main


import business.core.AppDataStore
import business.core.BaseUseCase
import business.core.ProgressBarState
import business.datasource.network.common.MainGenericResponse
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.NotificationDTO
import business.datasource.network.main.responses.toNotification
import business.domain.main.Notification

class GetNotificationsUseCase(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) : BaseUseCase<Unit, List<NotificationDTO>, List<Notification>>(appDataStoreManager) {

    override suspend fun run(
        params: Unit,
        token: String
    ) = service.getNotifications(token = token)

    override fun mapApiResponse(apiResponse: MainGenericResponse<List<NotificationDTO>>?) =
        apiResponse?.result?.map { it.toNotification() }


    override val progressBarType = ProgressBarState.LoadingWithLogo
    override val needNetworkState = true
    override val createException = true
    override val checkToken = true


}