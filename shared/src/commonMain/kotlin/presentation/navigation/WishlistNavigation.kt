package presentation.navigation

sealed class WishlistNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Wishlist : WishlistNavigation(route = "Home")

    object Detail : WishlistNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

