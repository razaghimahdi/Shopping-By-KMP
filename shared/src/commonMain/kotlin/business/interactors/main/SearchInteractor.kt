package business.interactors.main


import business.constants.DataStoreKeys
import business.core.AppDataStore
import business.core.DataState
import business.core.ProgressBarState
import business.core.UIComponent
import business.datasource.network.main.MainService
import business.datasource.network.main.responses.toSearch
import business.domain.main.Category
import business.domain.main.Search
import business.util.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchInteractor(
    private val service: MainService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(
        minPrice: Int? = null,
        maxPrice: Int? = null,
        categories: List<Category>? = null,
        sort: Int?,
        page: Int,
    ): Flow<DataState<Search>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ScreenLoading))

            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""

            val apiResponse = service.search(
                token = token,
                minPrice = minPrice,
                maxPrice = maxPrice,
                sort = sort,
                categoriesId = categories?.map { it.id }?.joinToString(","),
                page = page
            )


            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }

            val result = apiResponse.result?.toSearch()


            emit(DataState.Data(result))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}