package presentation.navigation

sealed class SplashNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {

    object Splash : SplashNavigation(route = "Splash")

    object Login : SplashNavigation(route = "Login")

    object Register : SplashNavigation(route = "Register")


}

