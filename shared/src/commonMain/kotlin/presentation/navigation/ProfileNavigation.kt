package presentation.navigation

sealed class ProfileNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
   data object Profile : ProfileNavigation(route = "Profile")
   data object Address : ProfileNavigation(route = "Address")
   data object EditProfile : ProfileNavigation(route = "EditProfile")
   data object PaymentMethod : ProfileNavigation(route = "PaymentMethod")
   data object MyOrders : ProfileNavigation(route = "MyOrders")
   data object MyCoupons : ProfileNavigation(route = "MyCoupons")
   data object MyWallet : ProfileNavigation(route = "MyWallet")
   data object Settings : ProfileNavigation(route = "Settings")


}

