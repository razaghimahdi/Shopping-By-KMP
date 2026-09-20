package com.razzaghi.shopingbykmp.presentation.ui.main.home.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class HomeEvent : ViewEvent {

    data object OnRetryNetwork : HomeEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : HomeEvent()

    data class Like(val id: Long) : HomeEvent()

}
