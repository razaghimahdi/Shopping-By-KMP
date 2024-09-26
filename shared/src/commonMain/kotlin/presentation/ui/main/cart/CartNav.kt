package presentation.ui.main.cart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val navigator = rememberNavController()
    NavHost(
        startDestination = CartNavigation.Cart.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = CartNavigation.Cart.route) {
            val viewModel: CartViewModel = koinInject()
            CartScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToDetail = {
                    navigator.navigate(CartNavigation.Detail.route.plus("/$it"))
                }, navigateToCheckout = {
                    navigator.navigate(CartNavigation.Checkout.route)
                })
        }
        composable(route = CartNavigation.Checkout.route) {
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
        composable(route = CartNavigation.Address.route) {
            val viewModel: AddressViewModel = koinInject()
            AddressScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = { navigator.popBackStack() },
            )
        }
        composable(
            route = CartNavigation.Detail.route.plus("/{id}"),
            arguments = CartNavigation.Detail.arguments,
        ) { backStackEntry ->
            val argument = backStackEntry.arguments
            val id = argument?.getString("id")?.toIntOrNull()
            id?.let {
                DetailNav(it) {
                    navigator.popBackStack()
                }
            }
        }
    }
}
