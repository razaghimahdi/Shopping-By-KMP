package com.razzaghi.shopingbykmp.presentation.ui.main.my_coupons.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Coupons

data class MyCouponsState(
    val couponsList: List<Coupons> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
