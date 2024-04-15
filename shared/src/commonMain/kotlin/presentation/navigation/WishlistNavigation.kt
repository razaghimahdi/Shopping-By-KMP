package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class WishlistNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Wishlist : WishlistNavigation(route = "Home", arguments = emptyList())

    data object Detail : WishlistNavigation(route = "Detail",
        arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })
    )

}

