package presentation.navigation

sealed class WishlistNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
   data object Wishlist : WishlistNavigation(route = "Home")

   data object Detail : WishlistNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

