package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation {

    @Serializable
    data object Splash : AppNavigation

    @Serializable
    data object Main : AppNavigation

}
