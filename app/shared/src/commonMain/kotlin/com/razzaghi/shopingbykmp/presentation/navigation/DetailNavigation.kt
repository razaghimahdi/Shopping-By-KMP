package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface DetailNavigation {

    @Serializable
    data class Comment(val id: Long) : DetailNavigation

    @Serializable
    data object Detail : DetailNavigation

}

