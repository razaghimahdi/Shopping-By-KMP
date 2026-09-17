package com.razzaghi.shopingbykmp.presentation.ui.main.cart.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class CartEvent : ViewEvent {

    data class DeleteFromBasket(val id: Long) : CartEvent()

    data class AddProduct(val id: Long) : CartEvent()

    data object OnRetryNetwork : CartEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CartEvent()
}
