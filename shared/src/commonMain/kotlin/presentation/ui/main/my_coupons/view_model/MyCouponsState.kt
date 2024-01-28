package presentation.ui.main.my_coupons.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Coupons

data class MyCouponsState(
    val couponsList:List<Coupons> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
