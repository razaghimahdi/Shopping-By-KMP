package presentation.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import common.ChangeStatusBarColors
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.SplashNavigation
import presentation.ui.splash.view_model.LoginViewModel

@Composable
internal fun SplashNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavigator()

    ChangeStatusBarColors(Color.Black)
    NavHost(
        navigator = navigator,
        initialRoute = SplashNavigation.Splash.route,
    ) {
        scene(route = SplashNavigation.Splash.route) {
            SplashScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToMain = navigateToMain,
                navigateToLogin = {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login.route)
                })
        }
        scene(route = SplashNavigation.Login.route) {
            LoginScreen(
                navigateToMain = navigateToMain, navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register.route)
                },
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
        scene(route = SplashNavigation.Register.route) {
            RegisterScreen(
                navigateToMain = navigateToMain, popUp = {
                    navigator.popBackStack()
                }, state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
    }

}