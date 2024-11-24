package presentation.ui.main.categories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import presentation.navigation.CategoriesNavigation
import presentation.ui.main.categories.view_model.CategoriesViewModel
import presentation.ui.main.search.SearchNav

@Composable
fun CategoriesNav(popup: () -> Unit) {

    val navigator = rememberNavController()
    NavHost(
        startDestination = CategoriesNavigation.Categories,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {

        composable<CategoriesNavigation.Categories> {
            val viewModel: CategoriesViewModel = koinInject()
            CategoriesScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = popup,
            ) { categoryId ->
                navigator.navigate(
                    CategoriesNavigation.Search(categoryId)
                )
            }
        }

        composable<CategoriesNavigation.Search>{ backStackEntry ->

            val argument = backStackEntry.toRoute<CategoriesNavigation.Search>()
            val categoryId = argument.categoryId
            SearchNav(categoryId = categoryId, sort = null) {
                navigator.popBackStack()
            }
        }
    }
}

