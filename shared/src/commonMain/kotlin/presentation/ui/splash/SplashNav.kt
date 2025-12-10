package presentation.ui.splash

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import common.ChangeStatusBarColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject
import presentation.navigation.SplashNavigation
import presentation.ui.splash.view_model.LoginAction
import presentation.ui.splash.view_model.LoginViewModel

@Composable
internal fun SplashNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {

    val backStack = rememberNavBackStack(
        SavedStateConfiguration.DEFAULT,
        SplashNavigation.Splash
    )

    LaunchedEffect(viewModel) {
        delay(4000L)
        viewModel.action.onEach { effect ->
            when (effect) {
                LoginAction.Navigation.NavigateToMain -> {
                    navigateToMain()
                }

                LoginAction.Navigation.NavigateToLogin -> {
                    backStack.clear()
                    backStack.add(SplashNavigation.Login)
                }
            }
        }.collect {}
    }

    ChangeStatusBarColors(MaterialTheme.colorScheme.primary)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = { key ->
            val destination = key as SplashNavigation
            when (destination) {
                SplashNavigation.Splash -> NavEntry(key) {
                    SplashScreen()
                }

                SplashNavigation.Login -> NavEntry(key) {
                    LoginScreen(
                        navigateToRegister = {
                            backStack.add(SplashNavigation.Register)
                        },
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = { event -> viewModel.setEvent(event) }
                    )
                }

                SplashNavigation.Register -> NavEntry(key) {
                    RegisterScreen(
                        popUp = {
                            if (backStack.size > 1) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        },
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = { event -> viewModel.setEvent(event) }
                    )
                }

            }
        }
    )
}