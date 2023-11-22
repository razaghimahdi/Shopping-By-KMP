package presentation.ui.main.wishlist.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.domain.main.Category

sealed class WishlistEvent {


    object GetNextPage : WishlistEvent()

    data class OnUpdateSelectedCategory(val category: Category): WishlistEvent()

    data class LikeProduct(val id: Int) : WishlistEvent()

    object OnRemoveHeadFromQueue : WishlistEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : WishlistEvent()

    object OnRetryNetwork : WishlistEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : WishlistEvent()

}
