package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface CartNavigation {

    @Serializable
    data object Cart : CartNavigation

    @Serializable
    data object Checkout : CartNavigation

    @Serializable
    data object Address : CartNavigation

    @Serializable
    data class Detail(val id: Long) : CartNavigation

}

