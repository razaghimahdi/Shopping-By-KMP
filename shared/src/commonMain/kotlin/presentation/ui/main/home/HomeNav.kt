package presentation.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        startDestination = HomeNavigation.Home.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = HomeNavigation.Home.route) {
            val viewModel: HomeViewModel = koinInject()
            HomeScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToNotifications = {
                    navigator.navigate(HomeNavigation.Notification.route)
                },
                navigateToCategories = {
                    navigator.navigate(HomeNavigation.Categories.route)
                },
                navigateToSetting = {
                    navigator.navigate(HomeNavigation.Settings.route)
                },
                navigateToDetail = {
                    navigator.popBackStack()
                    navigator.navigate(HomeNavigation.Detail.route.plus("/$it"))
                }) { categoryId, sort ->
                navigator.navigate(
                    HomeNavigation.Search.route.plus("/${categoryId}").plus("/${sort}")
                )
            }
        }

        composable(route = HomeNavigation.Settings.route) {
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

        composable(
            route = HomeNavigation.Categories.route
        ) {
            CategoriesNav {
                navigator.popBackStack()
            }
        }
        composable(
            route = HomeNavigation.Search.route
                .plus("/{category_id}")
                .plus("/{sort}")
        ) { backStackEntry ->
            val argument = backStackEntry.arguments
            val categoryId = argument?.getInt("category_id")
            val sort = argument?.getInt("sort")
            SearchNav(categoryId = categoryId, sort = sort) {
                navigator.popBackStack()
            }
        }
        composable(route = HomeNavigation.Detail.route.plus("/{id}")) { backStackEntry ->
            val argument = backStackEntry.arguments
            val id = argument?.getInt("id")
            id?.let {
                DetailNav(it) {
                    navigator.popBackStack()
                }
            }
        }
        composable(route = HomeNavigation.Notification.route) {
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
