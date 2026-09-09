package presentation.ui.main.wishlist.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent
import business.domain.main.Category

sealed class WishlistEvent : ViewEvent {

    data object GetNextPage : WishlistEvent()

    data class OnUpdateSelectedCategory(val category: Category) : WishlistEvent()

    data class LikeProduct(val id: Long) : WishlistEvent()

    data object OnRetryNetwork : WishlistEvent()

    data class OnUpdateNetworkState(val networkState: NetworkState) : WishlistEvent()

}
