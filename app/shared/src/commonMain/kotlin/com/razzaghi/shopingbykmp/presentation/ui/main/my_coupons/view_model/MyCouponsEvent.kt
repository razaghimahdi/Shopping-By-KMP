package com.razzaghi.shopingbykmp.presentation.ui.main.my_coupons.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class MyCouponsEvent : ViewEvent {

    data object OnRetryNetwork : MyCouponsEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyCouponsEvent()
}
