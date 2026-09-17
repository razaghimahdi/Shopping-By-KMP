package com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewEvent
import com.razzaghi.shopingbykmp.business.domain.main.Address
import com.razzaghi.shopingbykmp.business.domain.main.ShippingType

sealed class CheckoutEvent : ViewEvent {

    data class OnUpdateSelectedShipping(val value: ShippingType) : CheckoutEvent()

    data class OnUpdateSelectShippingDialogState(val value: UIComponentState) : CheckoutEvent()

    data class OnUpdateSelectedAddress(val value: Address) : CheckoutEvent()

    data object BuyProduct : CheckoutEvent()

    data object OnRetryNetwork : CheckoutEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CheckoutEvent()
}
