package presentation.ui.main.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.HomeNavigation
import presentation.ui.main.detail.DetailScreen
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel
import presentation.ui.main.home.view_model.HomeViewModel

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
                navigateToDetail = {
                    navigator.popBackStack()
                    navigator.navigate(HomeNavigation.Detail.route.plus("/$it"))
                })
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