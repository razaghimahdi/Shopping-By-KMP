package presentation.ui.main.cart.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState
import business.domain.main.Basket

data class CartState(
    val baskets: List<Basket> = listOf(),
    val totalCost: String = "",
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
