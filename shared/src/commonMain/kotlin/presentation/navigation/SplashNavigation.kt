package presentation.navigation

sealed class SplashNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {

    object Splash : AppNavigation(route = "Splash")

    object Login : AppNavigation(route = "Login")

    object Register : AppNavigation(route = "Register")


}

