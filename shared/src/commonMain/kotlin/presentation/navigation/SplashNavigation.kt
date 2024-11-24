package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface SplashNavigation {

    @Serializable
    data object Splash : SplashNavigation

    @Serializable
    data object Login : SplashNavigation

    @Serializable
    data object Register : SplashNavigation

}

