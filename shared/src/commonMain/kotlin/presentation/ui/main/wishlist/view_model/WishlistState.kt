package presentation.ui.main.wishlist.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState
import business.domain.main.Category
import business.domain.main.Wishlist
import business.domain.main.category_all

data class WishlistState(
    val categoryId: Long? = null,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val wishlist: Wishlist = Wishlist(),
    val selectedCategory: Category = category_all,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
