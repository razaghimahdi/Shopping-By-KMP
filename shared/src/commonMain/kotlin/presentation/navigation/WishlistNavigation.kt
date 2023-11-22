package presentation.navigation

sealed class WishlistNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Wishlist : AppNavigation(route = "Home")

    object Detail : AppNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

