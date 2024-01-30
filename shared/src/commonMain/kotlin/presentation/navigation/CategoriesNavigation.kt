package presentation.navigation

sealed class CategoriesNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = "",
) {
   data object Categories : CategoriesNavigation(route = "Categories")


   data object Search : CategoriesNavigation(
        route = "Search",
        objectName = "category_id",
        objectPath = "/{category_id}",
    )

}

