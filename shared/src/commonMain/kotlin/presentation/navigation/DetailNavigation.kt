package presentation.navigation

sealed class DetailNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Comment : DetailNavigation(route = "Comment", objectName = "id", objectPath = "/{id}")

    object Detail : DetailNavigation(route = "Detail")

}

