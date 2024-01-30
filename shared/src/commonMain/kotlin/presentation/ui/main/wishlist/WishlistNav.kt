package presentation.ui.main.wishlist

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.WishlistNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.wishlist.view_model.WishlistViewModel

@Composable
fun WishlistNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = WishlistNavigation.Wishlist.route,
    ) {
        scene(route = WishlistNavigation.Wishlist.route) {
            val viewModel: WishlistViewModel = koinInject()
            WishlistScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            ) {
                navigator.popBackStack()
                navigator.navigate(WishlistNavigation.Detail.route.plus("/$it"))
            }
        }
        scene(route = WishlistNavigation.Detail.route.plus(WishlistNavigation.Detail.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(WishlistNavigation.Detail.objectName)
            id?.let {
                DetailNav(it){
                    navigator.popBackStack()
                }
            }
        }
    }
}