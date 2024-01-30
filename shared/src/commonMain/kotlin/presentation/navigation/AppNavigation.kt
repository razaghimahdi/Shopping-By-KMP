package presentation.navigation

sealed class AppNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {

   data object Splash : AppNavigation(route = "Splash")

   data object Main : AppNavigation(route = "Main")


}
