package com.razzaghi.shopingbykmp.presentation.ui.main.detail.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class DetailEvent : ViewEvent {

    data class Like(val id: Long) : DetailEvent()

    data class AddBasket(val id: Long) : DetailEvent()

    data class OnUpdateSelectedImage(val value: String) : DetailEvent()

    data class GetProduct(val id: Long) : DetailEvent()

    data object OnRetryNetwork : DetailEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : DetailEvent()

}
