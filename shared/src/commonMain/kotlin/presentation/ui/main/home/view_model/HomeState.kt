package presentation.ui.main.home.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.ViewState
import business.domain.main.Home
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class HomeState(
    val home: Home = Home(),
    val time: LocalDateTime? = null,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
