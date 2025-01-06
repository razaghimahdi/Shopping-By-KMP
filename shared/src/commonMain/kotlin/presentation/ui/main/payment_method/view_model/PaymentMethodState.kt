package presentation.ui.main.payment_method.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState

data class PaymentMethodState(
    val selectedPaymentMethod: Int = 0,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
