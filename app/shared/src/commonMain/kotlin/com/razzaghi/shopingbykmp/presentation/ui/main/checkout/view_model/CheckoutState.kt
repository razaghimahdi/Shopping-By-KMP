package com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Address
import com.razzaghi.shopingbykmp.business.domain.main.ShippingType

data class CheckoutState(
    val totalCost: Long = 0L,
    val totalBasket: Long = 0L,
    val addresses: List<Address> = listOf(),
    val selectedAddress: Address = Address(),
    val selectedShipping: ShippingType = shippingType_global.first(),
    val selectShippingDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
