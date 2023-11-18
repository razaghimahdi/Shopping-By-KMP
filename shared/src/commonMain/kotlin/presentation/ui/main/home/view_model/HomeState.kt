package presentation.ui.main.home.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Home

data class HomeState(
    val home: Home = Home(),

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
