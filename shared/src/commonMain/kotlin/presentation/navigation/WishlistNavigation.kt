package presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface WishlistNavigation : NavKey {

    @Serializable
    data object Wishlist : WishlistNavigation

    @Serializable
    data class Detail(val id: Long) : WishlistNavigation

}

