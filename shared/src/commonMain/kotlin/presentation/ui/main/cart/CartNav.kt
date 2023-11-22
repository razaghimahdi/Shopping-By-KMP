package presentation.ui.main.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.CartNavigation
import presentation.ui.main.cart.view_model.CartViewModel
import presentation.ui.main.detail.DetailScreen
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel

@Composable
fun CartNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = CartNavigation.Cart.route,
    ) {
        scene(route = CartNavigation.Cart.route) {
            val viewModel: CartViewModel = koinInject()
            CartScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            ) {
                navigator.popBackStack()
                navigator.navigate(CartNavigation.Detail.route.plus("/$it"))
            }
        }
        scene(route = CartNavigation.Detail.route.plus(CartNavigation.Detail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(CartNavigation.Detail.objectName)
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