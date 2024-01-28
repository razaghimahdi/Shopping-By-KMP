package presentation.navigation

sealed class HomeNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = "",
    val objectName2: String = "",
    val objectPath2: String = "",
) {
    object Search : HomeNavigation(
        route = "Search",
        objectName = "category_id",
        objectPath = "/{category_id}",
        objectName2 = "sort",
        objectPath2 = "/{sort}",
    )

    object Home : HomeNavigation(route = "Home")

    object Notification : HomeNavigation(route = "Notification")

    object Categories : HomeNavigation(route = "Categories")

    object Settings : HomeNavigation(route = "Setting")

    object Detail : HomeNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

