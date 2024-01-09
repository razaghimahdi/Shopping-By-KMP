package presentation.ui.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.ProfileNavigation
import presentation.ui.main.profile.view_model.ProfileViewModel

@Composable
fun ProfileNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = ProfileNavigation.Profile.route,
    ) {
        scene(route = ProfileNavigation.Profile.route) {
            val viewModel: ProfileViewModel = koinInject()
            ProfileScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                )
        }
    }
}