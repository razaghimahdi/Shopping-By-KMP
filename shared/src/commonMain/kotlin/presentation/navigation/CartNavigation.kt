package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class CartNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Cart : CartNavigation(route = "Cart", arguments = emptyList())

    data object Checkout : CartNavigation(route = "Checkout", arguments = emptyList())

    data object Address : CartNavigation(route = "Address", arguments = emptyList())

    data object Detail : CartNavigation(route = "Detail",
        arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })
    )

}

