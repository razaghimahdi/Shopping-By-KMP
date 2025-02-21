package presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ProfileNavigation {

    @Serializable
    data object Profile : ProfileNavigation

    @Serializable
    data object Address : ProfileNavigation

    @Serializable
    data object AddAddress : ProfileNavigation

    @Serializable
    data object AddAddressInformation : ProfileNavigation

    @Serializable
    data object EditProfile : ProfileNavigation

    @Serializable
    data object PaymentMethod : ProfileNavigation

    @Serializable
    data object MyOrders : ProfileNavigation

    @Serializable
    data object MyCoupons : ProfileNavigation

    @Serializable
    data object MyWallet : ProfileNavigation

    @Serializable
    data object Settings : ProfileNavigation


}

