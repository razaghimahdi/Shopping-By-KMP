package presentation.navigation

sealed class ProfileNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Profile : AppNavigation(route = "Profile")


}

