package presentation.ui.main.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import presentation.navigation.WishlistNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.wishlist.view_model.WishlistViewModel

@Composable
fun WishlistNav() {
    val navigator = rememberNavController()
    NavHost(
        startDestination = WishlistNavigation.Wishlist,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<WishlistNavigation.Wishlist> {
            val viewModel: WishlistViewModel = koinInject()
            WishlistScreen(
                state = viewModel.state.value,
                errors = viewModel.errors,
                events = viewModel::onTriggerEvent
            ) {
                navigator.navigate(WishlistNavigation.Detail(it))
            }
        }
        composable<WishlistNavigation.Detail> { backStackEntry ->

            val argument = backStackEntry.toRoute<WishlistNavigation.Detail>()
            val id = argument.id
            DetailNav(id) {
                navigator.popBackStack()
            }
        }
    }
}