package com.razzaghi.shopingbykmp.presentation.ui.main.payment_method.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewState

data class PaymentMethodState(
    val selectedPaymentMethod: Int = 0,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
