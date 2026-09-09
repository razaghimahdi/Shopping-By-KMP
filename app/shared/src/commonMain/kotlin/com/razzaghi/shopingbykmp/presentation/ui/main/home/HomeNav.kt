package presentation.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import business.domain.main.Category
import org.koin.compose.koinInject
import presentation.navigation.HomeNavigation
import presentation.ui.main.categories.CategoriesScreen
import presentation.ui.main.categories.view_model.CategoriesViewModel
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.home.view_model.HomeViewModel
import presentation.ui.main.notifications.NotificationsScreen
import presentation.ui.main.notifications.view_model.NotificationsViewModel
import presentation.ui.main.search.SearchScreen
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchViewModel
import presentation.ui.main.settings.SettingsScreen
import presentation.ui.main.settings.view_model.SettingsViewModel

@Composable
fun HomeNav(logout: () -> Unit) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = HomeNavigation.Home,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<HomeNavigation.Home> {
            val viewModel: HomeViewModel = koinInject()
            HomeScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToNotifications = {
                    navigator.navigate(HomeNavigation.Notification)
                },
                navigateToCategories = {
                    navigator.navigate(HomeNavigation.Categories)
                },
                navigateToSetting = {
                    navigator.navigate(HomeNavigation.Settings)
                },
                navigateToDetail = {
                    navigator.navigate(HomeNavigation.Detail(id = it))
                }) { categoryId, sort ->
                navigator.navigate(
                    HomeNavigation.Search(categoryId = categoryId, sort = sort)
                )
            }
        }

        composable<HomeNavigation.Settings>{
            val viewModel: SettingsViewModel = koinInject()
            SettingsScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                action = viewModel.action,
                logout = logout,
                popup = {
                    navigator.popBackStack()
                },
            )
        }

        composable<HomeNavigation.Categories> {
            val viewModel: CategoriesViewModel = koinInject()
            CategoriesScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                popup = {
                    navigator.popBackStack()
                },
            ) { categoryId ->
                navigator.navigate(
                    HomeNavigation.Search(categoryId=categoryId,sort = null)
                )
            }
        }



        composable<HomeNavigation.Search> {backStackEntry->
            val viewModel: SearchViewModel = koinInject()
            val argument = backStackEntry.toRoute<HomeNavigation.Search>()
            val categoryId = argument.categoryId
            val sort = argument.sort
            LaunchedEffect(categoryId){
                val categories = if (categoryId != null) listOf(Category(id = categoryId)) else null
                sort?.let {
                    viewModel.onTriggerEvent(SearchEvent.OnUpdateSelectedSort(sort))
                }
                if (categoryId != null || sort != null) {
                    viewModel.onTriggerEvent(SearchEvent.Search(categories = categories))
                }
            }
            SearchScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToDetailScreen = {
                    navigator.navigate(HomeNavigation.Detail(it))
                },
                popUp = {
                    navigator.popBackStack()
                }
            )
        }

        composable<HomeNavigation.Detail>{ backStackEntry ->
            val argument = backStackEntry.toRoute<HomeNavigation.Detail>()
            val id = argument.id
                DetailNav(id) {
                    navigator.popBackStack()
                }
        }
        composable<HomeNavigation.Notification>{
            val viewModel: NotificationsViewModel = koinInject()
            NotificationsScreen(
                state = viewModel.state.value,
                errors = viewModel.errors,
                events = viewModel::onTriggerEvent,
                popup = {
                    navigator.popBackStack()
                },
            )
        }

    }
}
