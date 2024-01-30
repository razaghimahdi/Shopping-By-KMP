package presentation.ui.main.categories

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.CategoriesNavigation
import presentation.ui.main.categories.view_model.CategoriesViewModel
import presentation.ui.main.search.SearchNav

@Composable
fun CategoriesNav(popup: () -> Unit) {

    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = CategoriesNavigation.Categories.route,
    ) {

        scene(route = CategoriesNavigation.Categories.route) {
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

        scene(
            route = CategoriesNavigation.Search.route
                .plus(CategoriesNavigation.Search.objectPath)
        ) { backStackEntry ->
            val categoryId: Int? = backStackEntry.path<Int>(CategoriesNavigation.Search.objectName)
            SearchNav(categoryId = categoryId, sort = null) {
                navigator.popBackStack()
            }
        }
    }
}

