package presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeNavigation : NavKey {

    @Serializable
    data class Search(val categoryId: Long?, val sort: Int?) : HomeNavigation

    @Serializable
    data object Home : HomeNavigation

    @Serializable
    data object Notification : HomeNavigation

    @Serializable
    data object Categories : HomeNavigation

    @Serializable
    data object Settings : HomeNavigation

    @Serializable
    data class Detail(val id: Long) : HomeNavigation

}

