package presentation.ui.main.wishlist.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.domain.main.Category

sealed class WishlistEvent {


   data object GetNextPage : WishlistEvent()

    data class OnUpdateSelectedCategory(val category: Category): WishlistEvent()

    data class LikeProduct(val id: Int) : WishlistEvent()

   data object OnRemoveHeadFromQueue : WishlistEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : WishlistEvent()

   data object OnRetryNetwork : WishlistEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : WishlistEvent()

}
