package com.razzaghi.shopingbykmp.presentation.ui.main.home.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Home
import kotlin.time.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class HomeState(
    val home: Home = Home(),
    val time: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
