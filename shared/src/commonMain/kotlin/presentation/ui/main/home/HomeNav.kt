package presentation.ui.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.HomeNavigation
import presentation.ui.main.categories.CategoriesNav
import presentation.ui.main.detail.DetailScreen
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel
import presentation.ui.main.home.view_model.HomeViewModel
import presentation.ui.main.search.SearchNav

@Composable
fun HomeNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = HomeNavigation.Home.route,
    ) {
        scene(route = HomeNavigation.Home.route) {
            val viewModel: HomeViewModel = koinInject()
            HomeScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToCategories = {
                    navigator.navigate(HomeNavigation.Categories.route)
                },
                navigateToDetail = {
                    navigator.popBackStack()
                    navigator.navigate(HomeNavigation.Detail.route.plus("/$it"))
                }) { category_id, sort ->
                navigator.navigate(
                    HomeNavigation.Search.route.plus("/${category_id}").plus("/${sort}")
                )
            }
        }
        scene(
            route = HomeNavigation.Categories.route
        ) {
            CategoriesNav {
                navigator.popBackStack()
            }
        }
        scene(
            route = HomeNavigation.Search.route
                .plus(HomeNavigation.Search.objectPath)
                .plus(HomeNavigation.Search.objectPath2)
        ) { backStackEntry ->
            val categoryId: Int? = backStackEntry.path<Int>(HomeNavigation.Search.objectName)
            val sort: Int? = backStackEntry.path<Int>(HomeNavigation.Search.objectName2)
            SearchNav(categoryId = categoryId, sort = sort) {
                navigator.popBackStack()
            }
        }
        scene(route = HomeNavigation.Detail.route.plus(HomeNavigation.Detail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(HomeNavigation.Detail.objectName)
            id?.let {
                val viewModel: DetailViewModel = koinInject()
                LaunchedEffect(id) {
                    viewModel.onTriggerEvent(DetailEvent.GetProduct(id))
                }
                DetailScreen(
                    state = viewModel.state.value,
                    events = viewModel::onTriggerEvent,
                    popup = {
                        navigator.popBackStack()
                    })
            }
        }
    }
}