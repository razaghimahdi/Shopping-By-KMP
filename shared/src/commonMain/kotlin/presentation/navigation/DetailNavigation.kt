package presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface DetailNavigation: NavKey {

    @Serializable
    data class Comment(val id: Long) : DetailNavigation

    @Serializable
    data object Detail : DetailNavigation

}

