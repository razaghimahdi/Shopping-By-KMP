package com.razzaghi.shopingbykmp.presentation.ui.main.profile.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class ProfileEvent : ViewEvent {

    data object OnRetryNetwork : ProfileEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : ProfileEvent()

}
