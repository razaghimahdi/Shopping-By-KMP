package presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.ChangeStatusBarColors
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.navigation.MainNavigation
import presentation.theme.DefaultNavigationBarItemTheme
import presentation.ui.main.cart.CartNav
import presentation.ui.main.home.HomeNav
import presentation.ui.main.profile.ProfileNav
import presentation.ui.main.wishlist.WishlistNav

@Composable
fun MainNav(logout: () -> Unit) {

    val navigator = rememberNavigator()

    ChangeStatusBarColors(Color.White)
    Scaffold(bottomBar = {
        BottomNavigationUI(navigator)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navigator = navigator,
                initialRoute = MainNavigation.Home.route,
            ) {
                scene(route = MainNavigation.Home.route) {
                    HomeNav(logout = logout)
                }
                scene(route = MainNavigation.Wishlist.route) {
                    WishlistNav()
                }
                scene(route = MainNavigation.Cart.route) {
                    CartNav()
                }
                scene(route = MainNavigation.Profile.route) {
                    ProfileNav(logout = logout)
                }
            }
        }

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavigationUI(navigator: Navigator) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp
        ) {

            val items = listOf(
                MainNavigation.Home,
                MainNavigation.Wishlist,
                MainNavigation.Cart,
                MainNavigation.Profile,
            )
            items.forEach {
                NavigationBarItem(label = { Text(text = it.title) },
                    colors = DefaultNavigationBarItemTheme(),
                    selected = it.route == currentRoute(navigator),
                    icon = {
                        Icon(
                            painterResource(if (it.route == currentRoute(navigator)) it.selectedIcon else it.unSelectedIcon),
                            it.title
                        )
                    },
                    onClick = {
                        navigator.navigate(
                            it.route,
                            NavOptions(
                                launchSingleTop = true,
                            ),
                        )
                    })
            }
        }
    }
}


@Composable
fun currentRoute(navigator: Navigator): String? {
    return navigator.currentEntry.collectAsState(null).value?.route?.route

}