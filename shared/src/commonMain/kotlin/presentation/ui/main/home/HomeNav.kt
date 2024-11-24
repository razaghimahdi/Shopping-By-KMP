package presentation.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import presentation.navigation.HomeNavigation
import presentation.ui.main.categories.CategoriesNav
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.home.view_model.HomeViewModel
import presentation.ui.main.notifications.NotificationsScreen
import presentation.ui.main.notifications.view_model.NotificationsViewModel
import presentation.ui.main.search.SearchNav
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
                logout = logout,
                popup = {
                    navigator.popBackStack()
                },
            )
        }

        composable<HomeNavigation.Categories> {
            CategoriesNav {
                navigator.popBackStack()
            }
        }
        composable<HomeNavigation.Search>{ backStackEntry ->
            val argument = backStackEntry.toRoute<HomeNavigation.Search>()
            val categoryId = argument.categoryId
            val sort = argument.sort
            SearchNav(categoryId = categoryId, sort = sort) {
                navigator.popBackStack()
            }
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
                events = viewModel::onTriggerEvent,
                popup = {
                    navigator.popBackStack()
                },
            )
        }

    }
}
