package presentation.ui.main.search.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Category
import business.interactors.main.GetSearchFilterInteractor
import business.interactors.main.SearchInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val getSearchFilterInteractor: GetSearchFilterInteractor,
) : ViewModel() {



    val state: MutableState<SearchState> = mutableStateOf(SearchState())


    fun onTriggerEvent(event: SearchEvent) {
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

            is SearchEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is SearchEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
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
        state.value = state.value.copy(selectedSort = value)
    }

    private fun onUpdatePriceRange(value: ClosedFloatingPointRange<Float>) {
        state.value = state.value.copy(selectedRange = value)
    }

    private fun onUpdateSortDialogState(value: UIComponentState) {
        state.value = state.value.copy(sortDialogState = value)
    }

    private fun onUpdateFilterDialogState(value: UIComponentState) {
        state.value = state.value.copy(filterDialogState = value)
    }

    private fun onUpdateSearchText(value: String) {
        state.value = state.value.copy(searchText = value)
    }

    private fun getSearchFilter() {
        getSearchFilterInteractor.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        onTriggerEvent(SearchEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value = state.value.copy(searchFilter = it)

                            state.value = state.value.copy(
                                selectedRange = it.minPrice.toFloat()..it.maxPrice.toFloat()
                            )
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
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
                        onTriggerEvent(SearchEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value =
                                state.value.copy(search = it)
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun resetPaging() {
        state.value = state.value.copy(page = 1)
        state.value = state.value.copy(hasNextPage = true)
    }

    private fun getNextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
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
                        onTriggerEvent(SearchEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value =
                                state.value.copy(search = it)
                            if (it.products.isEmpty()) {
                                state.value = state.value.copy(hasNextPage = false)
                            }
                        }
                    }

                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun onUpdateSelectedCategory(categories: List<Category>) {
        state.value = state.value.copy(selectedCategory = categories)
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getSearchFilter()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}