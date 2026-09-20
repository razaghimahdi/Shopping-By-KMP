package com.razzaghi.shopingbykmp.business.interactors.main


import com.razzaghi.shopingbykmp.business.core.AppDataStore
import com.razzaghi.shopingbykmp.business.core.BaseUseCase
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.datasource.network.common.MainGenericResponse
import com.razzaghi.shopingbykmp.business.datasource.network.main.MainService
import com.razzaghi.shopingbykmp.business.datasource.network.main.responses.NotificationDTO
import com.razzaghi.shopingbykmp.business.domain.main.toNotification
import com.razzaghi.shopingbykmp.business.domain.main.Notification

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