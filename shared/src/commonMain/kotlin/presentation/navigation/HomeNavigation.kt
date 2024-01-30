package presentation.navigation

sealed class HomeNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = "",
    val objectName2: String = "",
    val objectPath2: String = "",
) {
   data object Search : HomeNavigation(
        route = "Search",
        objectName = "category_id",
        objectPath = "/{category_id}",
        objectName2 = "sort",
        objectPath2 = "/{sort}",
    )

   data object Home : HomeNavigation(route = "Home")

   data object Notification : HomeNavigation(route = "Notification")

   data object Categories : HomeNavigation(route = "Categories")

   data object Settings : HomeNavigation(route = "Setting")

   data object Detail : HomeNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

