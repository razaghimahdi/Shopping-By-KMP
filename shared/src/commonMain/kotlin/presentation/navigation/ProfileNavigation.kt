package presentation.navigation

sealed class ProfileNavigation(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Profile : ProfileNavigation(route = "Profile")
    object Address : ProfileNavigation(route = "Address")
    object EditProfile : ProfileNavigation(route = "EditProfile")
    object PaymentMethod : ProfileNavigation(route = "PaymentMethod")
    object MyOrders : ProfileNavigation(route = "MyOrders")
    object MyCoupons : ProfileNavigation(route = "MyCoupons")
    object MyWallet : ProfileNavigation(route = "MyWallet")
    object Settings : ProfileNavigation(route = "Settings")


}

