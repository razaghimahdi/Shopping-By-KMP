package presentation.ui.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
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
    val backStack = rememberNavBackStack(
        SavedStateConfiguration.DEFAULT,
        HomeNavigation.Home
    )

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = { key ->
            when (val destination = key as HomeNavigation) {
                HomeNavigation.Home -> NavEntry(destination) {
                    val viewModel: HomeViewModel = koinInject()
                    HomeScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        navigateToNotifications = {
                            backStack.add(HomeNavigation.Notification)
                        },
                        navigateToCategories = {
                            backStack.add(HomeNavigation.Categories)
                        },
                        navigateToSetting = {
                            backStack.add(HomeNavigation.Settings)
                        },
                        navigateToDetail = {
                            backStack.add(HomeNavigation.Detail(id = it))
                        }) { categoryId, sort ->
                        backStack.add(
                            HomeNavigation.Search(categoryId = categoryId, sort = sort)
                        )
                    }
                }

                HomeNavigation.Settings -> NavEntry(destination) {
                    val viewModel: SettingsViewModel = koinInject()
                    SettingsScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        action = viewModel.action,
                        logout = logout,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    )
                }

                HomeNavigation.Categories -> NavEntry(destination) {
                    val viewModel: CategoriesViewModel = koinInject()
                    CategoriesScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    ) { categoryId ->
                        backStack.add(
                            HomeNavigation.Search(categoryId = categoryId, sort = null)
                        )
                    }
                }

                is HomeNavigation.Search -> NavEntry(destination) {
                    val viewModel: SearchViewModel = koinInject()
                    val categoryId = destination.categoryId
                    val sort = destination.sort

                    LaunchedEffect(categoryId) {
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
                            backStack.add(HomeNavigation.Detail(it))
                        },
                        popUp = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }

                is HomeNavigation.Detail -> NavEntry(destination) {
                    DetailNav(destination.id) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                HomeNavigation.Notification -> NavEntry(destination) {
                    val viewModel: NotificationsViewModel = koinInject()
                    NotificationsScreen(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    )
                }
            }
        }
    )
}