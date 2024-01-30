package presentation.navigation

sealed class CartNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
   data object Cart : CartNavigation(route = "Cart")

   data object Checkout : CartNavigation(route = "Checkout")

   data object Address : CartNavigation(route = "Address")

   data object Detail : CartNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

