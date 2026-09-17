package com.razzaghi.shopingbykmp.presentation.ui.main.categories.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class CategoriesEvent : ViewEvent {

    data object OnRetryNetwork : CategoriesEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CategoriesEvent()

}
