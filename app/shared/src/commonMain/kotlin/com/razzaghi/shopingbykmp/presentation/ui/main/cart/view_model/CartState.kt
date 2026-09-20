package com.razzaghi.shopingbykmp.presentation.ui.main.cart.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Basket

data class CartState(
    val baskets: List<Basket> = listOf(),
    val totalCost: String = "",
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
