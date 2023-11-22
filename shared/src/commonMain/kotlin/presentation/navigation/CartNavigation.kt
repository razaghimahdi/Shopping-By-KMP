package presentation.navigation

sealed class CartNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Cart : AppNavigation(route = "Cart")

    object Detail : AppNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

