package presentation.ui.main.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import presentation.navigation.WishlistNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.wishlist.view_model.WishlistViewModel

@Composable
fun WishlistNav() {
    val navigator = rememberNavController()
    NavHost(
        startDestination = WishlistNavigation.Wishlist.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = WishlistNavigation.Wishlist.route) {
            val viewModel: WishlistViewModel = koinInject()
            WishlistScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            ) {
                navigator.popBackStack()
                navigator.navigate(WishlistNavigation.Detail.route.plus("/$it"))
            }
        }
        composable(route = WishlistNavigation.Detail.route.plus("/{id}")) { backStackEntry ->

            val argument = backStackEntry.arguments
            val id = argument?.getInt("id")
            id?.let {
                DetailNav(it) {
                    navigator.popBackStack()
                }
            }
        }
    }
}