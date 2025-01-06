package presentation.ui.main.search.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.core.ViewState
import business.domain.main.Category
import business.domain.main.Search
import business.domain.main.SearchFilter

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
