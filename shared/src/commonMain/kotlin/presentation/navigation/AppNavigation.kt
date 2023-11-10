package presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable

sealed class AppNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {

    object Splash : AppNavigation(route = "Splash")

    object Main : AppNavigation(route = "Main")


}
