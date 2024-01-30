package presentation.navigation

sealed class SplashNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {

   data object Splash : SplashNavigation(route = "Splash")

   data object Login : SplashNavigation(route = "Login")

   data object Register : SplashNavigation(route = "Register")


}

