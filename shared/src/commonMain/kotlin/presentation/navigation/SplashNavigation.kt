package presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class SplashNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {

    data object Splash : SplashNavigation(route = "Splash", arguments = emptyList())

    data object Login : SplashNavigation(route = "Login", arguments = emptyList())

    data object Register : SplashNavigation(route = "Register", arguments = emptyList())


}

