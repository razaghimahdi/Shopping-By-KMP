package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class SearchNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Search : SearchNavigation(route = "Search", arguments = emptyList())

    data object Detail : SearchNavigation(route = "Detail",
        arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })
    )


}

