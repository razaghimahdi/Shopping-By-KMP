package presentation.navigation

sealed class HomeNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Home : AppNavigation(route = "Home")

    object Detail : AppNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

