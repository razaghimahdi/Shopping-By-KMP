package presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class ProfileNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
   data object Profile : ProfileNavigation(route = "Profile", arguments = emptyList())
   data object Address : ProfileNavigation(route = "Address", arguments = emptyList())
   data object EditProfile : ProfileNavigation(route = "EditProfile", arguments = emptyList())
   data object PaymentMethod : ProfileNavigation(route = "PaymentMethod", arguments = emptyList())
   data object MyOrders : ProfileNavigation(route = "MyOrders", arguments = emptyList())
   data object MyCoupons : ProfileNavigation(route = "MyCoupons", arguments = emptyList())
   data object MyWallet : ProfileNavigation(route = "MyWallet", arguments = emptyList())
   data object Settings : ProfileNavigation(route = "Settings", arguments = emptyList())


}

