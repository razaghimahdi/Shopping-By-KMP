package presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class CategoriesNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Categories : CategoriesNavigation(route = "Categories", arguments = emptyList())


    data object Search : CategoriesNavigation(
        route = "Search",
        arguments = listOf(navArgument("category_id") {
            type = NavType.IntType
        })
    )

}

