package presentation.navigation

sealed class CartNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Cart : CartNavigation(route = "Cart")

    object Checkout : CartNavigation(route = "Checkout")

    object Address : CartNavigation(route = "Address")

    object Detail : CartNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

