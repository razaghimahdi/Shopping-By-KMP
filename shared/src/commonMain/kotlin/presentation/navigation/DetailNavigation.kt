package presentation.navigation

sealed class DetailNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
   data object Comment : DetailNavigation(route = "Comment", objectName = "id", objectPath = "/{id}")

   data object Detail : DetailNavigation(route = "Detail")

}

