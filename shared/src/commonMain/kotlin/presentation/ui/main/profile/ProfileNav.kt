package presentation.ui.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.ProfileNavigation

@Composable
fun ProfileNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = ProfileNavigation.Profile.route,
    ) {
        scene(route = ProfileNavigation.Profile.route) {
            ProfileScreen()
        }
    }
}