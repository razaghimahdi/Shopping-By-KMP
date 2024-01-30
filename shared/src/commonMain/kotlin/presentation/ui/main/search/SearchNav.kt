package presentation.ui.main.search


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import business.domain.main.Category
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.SearchNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchViewModel

@Composable
fun SearchNav(categoryId: Int?, sort: Int?, popUp: () -> Unit) {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = SearchNavigation.Search.route,
    ) {
        scene(route = SearchNavigation.Search.route) {
            val viewModel: SearchViewModel = koinInject()
            LaunchedEffect(sort, categoryId) {
                val categories = if (categoryId != null) listOf(Category(id = categoryId)) else null
                sort?.let {
                    viewModel.onTriggerEvent(SearchEvent.OnUpdateSelectedSort(sort))
                }
                if (categoryId != null || sort != null) {
                    viewModel.onTriggerEvent(SearchEvent.Search(categories = categories))
                }
            }
            SearchScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToDetailScreen = {
                    navigator.popBackStack()
                    navigator.navigate(SearchNavigation.Detail.route.plus("/$it"))
                },
                popUp = { popUp() }
            )
        }
        scene(route = SearchNavigation.Detail.route.plus(SearchNavigation.Detail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(SearchNavigation.Detail.objectName)
            id?.let {
                DetailNav(it){
                    navigator.popBackStack()
                }
            }
        }
    }
}
