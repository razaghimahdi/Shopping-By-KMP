package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface WishlistNavigation {

    @Serializable
    data object Wishlist : WishlistNavigation

    @Serializable
    data class Detail(val id: Long) : WishlistNavigation

}

