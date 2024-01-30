package presentation.navigation



sealed class SearchNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = "",
) {
   data object Search : SearchNavigation(route = "Search")

   data object Detail : SearchNavigation(route = "Detail", objectName = "id", objectPath = "/{id}")


}

