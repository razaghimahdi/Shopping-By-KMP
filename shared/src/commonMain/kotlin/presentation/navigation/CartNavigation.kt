package presentation.navigation

sealed class CartNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Cart : CartNavigation(route = "Cart")

    object Detail : CartNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

