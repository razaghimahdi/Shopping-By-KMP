package presentation.navigation



sealed class SearchNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = "",
) {
    object Search : SearchNavigation(route = "Search",)

    object Detail : SearchNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")


}

