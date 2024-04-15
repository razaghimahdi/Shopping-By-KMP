package presentation.ui.main.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import presentation.navigation.CategoriesNavigation
import presentation.ui.main.categories.view_model.CategoriesViewModel
import presentation.ui.main.search.SearchNav

@Composable
fun CategoriesNav(popup: () -> Unit) {

    val navigator = rememberNavController()
    NavHost(
        startDestination = CategoriesNavigation.Categories.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {

        composable(route = CategoriesNavigation.Categories.route) {
            val viewModel: CategoriesViewModel = koinInject()
            CategoriesScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = popup,
            ) { categoryId ->
                navigator.navigate(
                    CategoriesNavigation.Search.route.plus("/${categoryId}")
                )
            }
        }

        composable(
            route = CategoriesNavigation.Search.route
                .plus("/{category_id}")
        ) { backStackEntry ->

            val argument = backStackEntry.arguments
            val categoryId = argument?.getInt("category_id")
            SearchNav(categoryId = categoryId, sort = null) {
                navigator.popBackStack()
            }
        }
    }
}

