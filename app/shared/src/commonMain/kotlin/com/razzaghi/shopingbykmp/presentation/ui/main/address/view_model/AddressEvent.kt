package com.razzaghi.shopingbykmp.presentation.ui.main.address.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class AddressEvent : ViewEvent {

    data object OnRetryNetwork : AddressEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : AddressEvent()
}
