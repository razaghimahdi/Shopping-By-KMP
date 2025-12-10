package presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface SplashNavigation : NavKey {

    @Serializable
    data object Splash : SplashNavigation

    @Serializable
    data object Login : SplashNavigation

    @Serializable
    data object Register : SplashNavigation

}

