package presentation.ui.main.search


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import business.domain.main.Category
import org.koin.compose.koinInject
import presentation.navigation.SearchNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchViewModel

@Composable
fun SearchNav(categoryId: Int?, sort: Int?, popUp: () -> Unit) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = SearchNavigation.Search.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = SearchNavigation.Search.route) {
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
        composable(route = SearchNavigation.Detail.route.plus("/{id}")) { backStackEntry ->

            val argument = backStackEntry.arguments
            val id = argument?.getInt("id")
            id?.let {
                DetailNav(it) {
                    navigator.popBackStack()
                }
            }
        }
    }
}
