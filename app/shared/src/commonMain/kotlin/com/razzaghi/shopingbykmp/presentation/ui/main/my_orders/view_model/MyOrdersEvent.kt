package com.razzaghi.shopingbykmp.presentation.ui.main.my_orders.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class MyOrdersEvent : ViewEvent {

    data object OnRetryNetwork : MyOrdersEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : MyOrdersEvent()
}
