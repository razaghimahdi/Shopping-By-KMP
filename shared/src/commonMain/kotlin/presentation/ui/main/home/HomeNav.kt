package presentation.ui.main.home

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.navigation.HomeNavigation
import presentation.ui.main.MainNav
import presentation.ui.main.detail.DetailScreen
import presentation.ui.splash.SplashNav

@Composable
fun HomeNav() {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = HomeNavigation.Home.route,
    ) {
        scene(route = HomeNavigation.Home.route) {
            HomeScreen(navigateToDetail = {
                navigator.popBackStack()
                navigator.navigate(HomeNavigation.Detail.route)
            })
        }
        scene(route = HomeNavigation.Detail.route) {
            DetailScreen()
        }
    }
}