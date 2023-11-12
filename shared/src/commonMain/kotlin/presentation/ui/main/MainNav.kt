package presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import presentation.navigation.MainNavigation
import presentation.theme.DefaultNavigationBarItemTheme
import presentation.ui.main.cart.CartScreen
import presentation.ui.main.detail.DetailScreen
import presentation.ui.main.home.HomeScreen
import presentation.ui.main.profile.ProfileScreen
import presentation.ui.main.wishlist.WishlistScreen

@Composable
fun MainNav() {

    val navigator = rememberNavigator()

    Scaffold(bottomBar = {
        BottomNavigationUI(navigator)
    }) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
        NavHost(
            navigator = navigator,
            initialRoute = MainNavigation.Home.route,
        ) {
            scene(route = MainNavigation.Home.route) {
                HomeScreen()
            }
            scene(route = MainNavigation.Wishlist.route) {
                WishlistScreen()
            }
            scene(route = MainNavigation.Cart.route) {
                CartScreen()
            }
            scene(route = MainNavigation.Profile.route) {
                ProfileScreen()
            }
        }
        }

    }
}


@Composable
fun BottomNavigationUI(navigator: Navigator) {
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
            NavigationBarItem(label = { Text(text = it.title) }, colors = DefaultNavigationBarItemTheme(),
                selected = it.route == currentRoute(navigator),
                icon = {
                    Icon(
                        if (it.route == currentRoute(navigator)) it.selectedIcon else it.unSelectedIcon,
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


@Composable
fun currentRoute(navigator: Navigator): String? {
    return navigator.currentEntry.collectAsState(null).value?.route?.route

}