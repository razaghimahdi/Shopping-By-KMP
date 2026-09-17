package com.razzaghi.shopingbykmp.presentation.ui.main.search.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewState
import com.razzaghi.shopingbykmp.business.domain.main.Category
import com.razzaghi.shopingbykmp.business.domain.main.Search
import com.razzaghi.shopingbykmp.business.domain.main.SearchFilter

data class SearchState(
    val selectedCategory: List<Category> = listOf(),
    val selectedRange: ClosedFloatingPointRange<Float> = 0f..10f,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val searchText: String = "",
    val searchFilter: SearchFilter = SearchFilter(),
    val search: Search = Search(),
    val selectedSort: Int = 0,
    val filterDialogState: UIComponentState = UIComponentState.Hide,
    val sortDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
