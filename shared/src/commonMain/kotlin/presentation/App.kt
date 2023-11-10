package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.navigation.AppNavigation
import presentation.theme.AppTheme
import presentation.ui.main.MainNav
import presentation.ui.splash.SplashNav
import presentation.ui.splash.SplashScreen

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalResourceApi::class)
@Composable
internal fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()


        AppTheme {
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


