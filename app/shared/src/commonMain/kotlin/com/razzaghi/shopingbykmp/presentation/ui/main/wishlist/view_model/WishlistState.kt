package com.razzaghi.shopingbykmp.presentation.ui.main.wishlist.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Category
import com.razzaghi.shopingbykmp.business.domain.main.Wishlist
import com.razzaghi.shopingbykmp.business.domain.main.category_all

data class WishlistState(
    val categoryId: Long? = null,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val wishlist: Wishlist = Wishlist(),
    val selectedCategory: Category = category_all,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
