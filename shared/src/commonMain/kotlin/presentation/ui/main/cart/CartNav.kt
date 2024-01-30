package presentation.ui.main.cart

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.CartNavigation
import presentation.ui.main.address.AddressScreen
import presentation.ui.main.address.view_model.AddressViewModel
import presentation.ui.main.cart.view_model.CartViewModel
import presentation.ui.main.checkout.CheckoutScreen
import presentation.ui.main.checkout.view_model.CheckoutViewModel
import presentation.ui.main.detail.DetailNav

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
                events = viewModel::onTriggerEvent,
                navigateToDetail = {
                    navigator.popBackStack()
                    navigator.navigate(CartNavigation.Detail.route.plus("/$it"))
                }, navigateToCheckout = {
                    navigator.navigate(CartNavigation.Checkout.route)
                })
        }
        scene(route = CartNavigation.Checkout.route) {
            val viewModel: CheckoutViewModel = koinInject()
            CheckoutScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToAddress = {
                    navigator.navigate(CartNavigation.Address.route)
                },
                popup = { navigator.popBackStack() },
            )
        }
        scene(route = CartNavigation.Address.route) {
            val viewModel: AddressViewModel = koinInject()
            AddressScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = { navigator.popBackStack() },
            )
        }
        scene(route = CartNavigation.Detail.route.plus(CartNavigation.Detail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(CartNavigation.Detail.objectName)
            id?.let {
                DetailNav(it) {
                    navigator.popBackStack()
                }
            }
        }
    }
}
