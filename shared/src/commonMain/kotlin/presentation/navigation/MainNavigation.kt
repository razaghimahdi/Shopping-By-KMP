package presentation.navigation

sealed class MainNavigation(
    val route: String,
    val title: String,
    val selectedIcon: String,
    val unSelectedIcon: String,
) {

   data object Home : MainNavigation(
        route = "Home", title = "Home",
        selectedIcon = "home.xml",
        unSelectedIcon = "home_border.xml"
    )

   data object Wishlist : MainNavigation(
        route = "Wishlist", title = "Wishlist",
        selectedIcon = "heart2.xml",
        unSelectedIcon = "heart_border2.xml"
    )

   data object Cart : MainNavigation(
        route = "Cart", title = "Cart",
        selectedIcon = "cart.xml",
        unSelectedIcon = "cart_border.xml"
    )

   data object Profile : MainNavigation(
        route = "Profile", title = "Profile",
        selectedIcon = "profile.xml",
        unSelectedIcon = "profile_border.xml"
    )


}

