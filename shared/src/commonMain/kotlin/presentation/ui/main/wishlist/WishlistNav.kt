package presentation.ui.main.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import org.koin.compose.koinInject
import presentation.navigation.WishlistNavigation
import presentation.ui.main.detail.DetailNav
import presentation.ui.main.wishlist.view_model.WishlistViewModel

@Composable
fun WishlistNav() {
    val backStack = rememberNavBackStack(
        SavedStateConfiguration.DEFAULT,
        WishlistNavigation.Wishlist
    )

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = { key ->
            when (val destination = key as WishlistNavigation) {
                WishlistNavigation.Wishlist -> NavEntry(destination) {
                    val viewModel: WishlistViewModel = koinInject()
                    WishlistScreen(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent
                    ) {
                        backStack.add(WishlistNavigation.Detail(it))
                    }
                }

                is WishlistNavigation.Detail -> NavEntry(destination) {
                    DetailNav(destination.id) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }
            }
        }
    )
}