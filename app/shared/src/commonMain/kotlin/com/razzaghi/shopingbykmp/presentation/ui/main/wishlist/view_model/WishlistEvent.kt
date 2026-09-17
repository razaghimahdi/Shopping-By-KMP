package com.razzaghi.shopingbykmp.presentation.ui.main.wishlist.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewEvent
import com.razzaghi.shopingbykmp.business.domain.main.Category

sealed class WishlistEvent : ViewEvent {

    data object GetNextPage : WishlistEvent()

    data class OnUpdateSelectedCategory(val category: Category) : WishlistEvent()

    data class LikeProduct(val id: Long) : WishlistEvent()

    data object OnRetryNetwork : WishlistEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : WishlistEvent()

}
