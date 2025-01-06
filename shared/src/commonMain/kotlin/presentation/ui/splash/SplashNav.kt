package presentation.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import common.ChangeStatusBarColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject
import presentation.navigation.SplashNavigation
import presentation.ui.splash.view_model.LoginAction
import presentation.ui.splash.view_model.LoginViewModel

@Composable
internal fun SplashNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavController()

    LaunchedEffect(viewModel) {
        delay(3000L)
        viewModel.action.onEach { effect ->
            when (effect) {
                LoginAction.Navigation.NavigateToMain -> {
                    navigateToMain()
                }

                LoginAction.Navigation.NavigateToLogin -> {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login)
                }

            }
        }.collect {}
    }


    ChangeStatusBarColors(Color.Black)
    NavHost(
        startDestination = SplashNavigation.Splash,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<SplashNavigation.Splash> {
            SplashScreen()
        }
        composable<SplashNavigation.Login> {
            LoginScreen(
                navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register)
                },
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = { event -> viewModel.setEvent(event) }
            )
        }
        composable<SplashNavigation.Register> {
            RegisterScreen(popUp = {
                navigator.popBackStack()
            }, state = viewModel.state.value,
                errors = viewModel.errors,
                events = { event -> viewModel.setEvent(event) }
            )
        }
    }

}