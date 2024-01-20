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
    object Categories : HomeNavigation(route = "Categories")

    object Detail : HomeNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")

}

