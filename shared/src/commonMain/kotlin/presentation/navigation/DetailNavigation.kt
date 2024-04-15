package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class DetailNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Comment : DetailNavigation(route = "Comment",
        arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })
    )

    data object Detail : DetailNavigation(route = "Detail", arguments = emptyList())

}

