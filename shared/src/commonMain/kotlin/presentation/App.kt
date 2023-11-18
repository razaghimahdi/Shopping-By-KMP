package presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import common.Context
import di.appModule
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinApplication
import presentation.navigation.AppNavigation
import presentation.theme.AppTheme
import presentation.ui.main.MainNav
import presentation.ui.splash.SplashNav

@Composable
internal fun App(context: Context) {

    KoinApplication(application = {
        modules(appModule(context))
    }) {
        PreComposeApp {

            AppTheme {
                val navigator = rememberNavigator()
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navigator = navigator,
                        initialRoute = AppNavigation.Splash.route,
                    ) {
                        scene(route = AppNavigation.Splash.route) {
                            SplashNav(navigateToMain = {
                                navigator.popBackStack()
                                navigator.navigate(AppNavigation.Main.route)
                            })
                        }
                        scene(route = AppNavigation.Main.route) {
                            MainNav()
                        }
                    }
                }

            }
        }
    }
}




