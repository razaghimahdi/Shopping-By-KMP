package presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation: NavKey {

    @Serializable
    data object Splash : AppNavigation

    @Serializable
    data object Main : AppNavigation

}
