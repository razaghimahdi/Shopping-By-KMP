package presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class AppNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {

   data object Splash : AppNavigation(route = "Splash", arguments = emptyList())

   data object Main : AppNavigation(route = "Main", arguments = emptyList())


}
