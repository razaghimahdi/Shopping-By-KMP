package presentation.ui.main.search.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.core.UIComponentState
import business.domain.main.Category
import business.interactors.main.GetSearchFilterInteractor
import business.interactors.main.SearchInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val getSearchFilterInteractor: GetSearchFilterInteractor,
) : BaseViewModel<SearchEvent, SearchState, Nothing>() {

    override fun setInitialState() = SearchState()

    override fun onTriggerEvent(event: SearchEvent) {
        when (event) {

            is SearchEvent.Search -> {
                search(
                    minPrice = event.minPrice,
                    maxPrice = event.maxPrice,
                    categories = event.categories,
                )
            }

            is SearchEvent.OnUpdateSelectedSort -> {
                onUpdateSelectedSort(event.value)
            }

            is SearchEvent.OnUpdatePriceRange -> {
                onUpdatePriceRange(event.value)
            }

            is SearchEvent.OnUpdateSortDialogState -> {
                onUpdateSortDialogState(event.value)
            }

            is SearchEvent.OnUpdateFilterDialogState -> {
                onUpdateFilterDialogState(event.value)
            }

            is SearchEvent.OnUpdateSearchText -> {
                onUpdateSearchText(event.value)
            }

            is SearchEvent.GetNextPage -> {
                getNextPage()
            }

            is SearchEvent.OnUpdateSelectedCategory -> {
                onUpdateSelectedCategory(categories = event.categories)
            }

            is SearchEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is SearchEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getSearchFilter()
    }


    private fun onUpdateSelectedSort(value: Int) {
        setState { copy(selectedSort = value) }
    }

    private fun onUpdatePriceRange(value: ClosedFloatingPointRange<Float>) {
        setState { copy(selectedRange = value) }
    }

    private fun onUpdateSortDialogState(value: UIComponentState) {
        setState { copy(sortDialogState = value) }
    }

    private fun onUpdateFilterDialogState(value: UIComponentState) {
        setState { copy(filterDialogState = value) }
    }

    private fun onUpdateSearchText(value: String) {
        setState { copy(searchText = value) }
    }

    private fun getSearchFilter() {
        getSearchFilterInteractor.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState {
                                copy(
                                    searchFilter = it,
                                    selectedRange = it.minPrice.toFloat()..it.maxPrice.toFloat()
                                )
                            }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun search(
        minPrice: Int? = null,
        maxPrice: Int? = null,
        categories: List<Category>? = null,
    ) {
        resetPaging()
        searchInteractor.execute(
            page = state.value.page,
            minPrice = minPrice,
            maxPrice = maxPrice,
            categories = categories,
            sort = state.value.selectedSort,
        )
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState { copy(search = it) }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun resetPaging() {
        setState { copy(page = 1, hasNextPage = true) }
    }

    private fun getNextPage() {
        setState { copy(page = state.value.page + 1) }
        searchInteractor.execute(
            page = state.value.page,
            minPrice = state.value.selectedRange.start.toInt(),
            maxPrice = state.value.selectedRange.endInclusive.toInt(),
            categories = state.value.selectedCategory,
            sort = state.value.selectedSort,
        )
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        setError { dataState.uiComponent }
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            setState { copy(search = it) }
                            if (it.products.isEmpty()) {
                                setState { copy(hasNextPage = false) }
                            }
                        }
                    }

                    is DataState.Loading -> {
                        setState { copy(progressBarState = dataState.progressBarState) }
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectedCategory(categories: List<Category>) {
        setState { copy(selectedCategory = categories) }
    }

    private fun onRetryNetwork() {
        getSearchFilter()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}