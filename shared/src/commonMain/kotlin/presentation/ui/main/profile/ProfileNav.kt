package presentation.ui.main.profile

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.ProfileNavigation
import presentation.ui.main.address.AddressScreen
import presentation.ui.main.address.view_model.AddressViewModel
import presentation.ui.main.edit_profile.EditProfileScreen
import presentation.ui.main.edit_profile.view_model.EditProfileViewModel
import presentation.ui.main.my_coupons.MyCouponsScreen
import presentation.ui.main.my_coupons.view_model.MyCouponsViewModel
import presentation.ui.main.my_orders.MyOrdersScreen
import presentation.ui.main.my_orders.view_model.MyOrdersViewModel
import presentation.ui.main.payment_method.PaymentMethodScreen
import presentation.ui.main.payment_method.view_model.PaymentMethodViewModel
import presentation.ui.main.profile.view_model.ProfileViewModel
import presentation.ui.main.settings.SettingsScreen
import presentation.ui.main.settings.view_model.SettingsViewModel

@Composable
fun ProfileNav(logout: () -> Unit) {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = ProfileNavigation.Profile.route,
    ) {
        scene(route = ProfileNavigation.Profile.route) {
            val viewModel: ProfileViewModel = koinInject()
            ProfileScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToAddress = {
                    navigator.navigate(ProfileNavigation.Address.route)
                },
                navigateToEditProfile = {
                    navigator.navigate(ProfileNavigation.EditProfile.route)
                },
                navigateToPaymentMethod = {
                    navigator.navigate(ProfileNavigation.PaymentMethod.route)
                },
                navigateToMyOrders = {
                    navigator.navigate(ProfileNavigation.MyOrders.route)
                },
                navigateToMyCoupons = {
                    navigator.navigate(ProfileNavigation.MyCoupons.route)
                },
                navigateToMyWallet = {
                    navigator.navigate(ProfileNavigation.MyWallet.route)
                },
                navigateToSettings = {
                    navigator.navigate(ProfileNavigation.Settings.route)
                },
            )
        }
        scene(route = ProfileNavigation.Settings.route) {
            val viewModel: SettingsViewModel = koinInject()
            SettingsScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                logout = logout,
                popup = {
                    navigator.popBackStack()
                },
            )
        }
        scene(route = ProfileNavigation.MyCoupons.route) {
            val viewModel: MyCouponsViewModel = koinInject()
            MyCouponsScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        scene(route = ProfileNavigation.MyWallet.route) {
            /*val viewModel: MyWalletViewModel = koinInject()
            MyWalletScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }*/
        }
        scene(route = ProfileNavigation.MyOrders.route) {
            val viewModel: MyOrdersViewModel = koinInject()
            MyOrdersScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        scene(route = ProfileNavigation.PaymentMethod.route) {
            val viewModel: PaymentMethodViewModel = koinInject()
            PaymentMethodScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        scene(route = ProfileNavigation.EditProfile.route) {
            val viewModel: EditProfileViewModel = koinInject()
            EditProfileScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        scene(route = ProfileNavigation.Address.route) {
            val viewModel: AddressViewModel = koinInject()
            AddressScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
    }
}



