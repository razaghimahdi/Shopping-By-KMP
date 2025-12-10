package presentation.ui.main.profile

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import common.Context
import org.koin.compose.koinInject
import presentation.navigation.ProfileNavigation
import presentation.ui.main.add_address.AddAddressInformationScreen
import presentation.ui.main.add_address.AddAddressScreen
import presentation.ui.main.add_address.view_model.AddAddressViewModel
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
fun ProfileNav(context: Context?, logout: () -> Unit) {
    // Shared ViewModel for Address flows (AddAddress -> AddAddressInformation)
    val addressViewModel: AddAddressViewModel = koinInject()

    val backStack = rememberNavBackStack(
        SavedStateConfiguration.DEFAULT,
        ProfileNavigation.Profile
    )

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },

        entryProvider = { key ->
            when (val destination = key as ProfileNavigation) {
                ProfileNavigation.Profile -> NavEntry(destination) {
                    val viewModel: ProfileViewModel = koinInject()
                    ProfileScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToAddress = {
                            backStack.add(ProfileNavigation.Address)
                        },
                        navigateToEditProfile = {
                            backStack.add(ProfileNavigation.EditProfile)
                        },
                        navigateToPaymentMethod = {
                            backStack.add(ProfileNavigation.PaymentMethod)
                        },
                        navigateToMyOrders = {
                            backStack.add(ProfileNavigation.MyOrders)
                        },
                        navigateToMyCoupons = {
                            backStack.add(ProfileNavigation.MyCoupons)
                        },
                        navigateToMyWallet = {
                            backStack.add(ProfileNavigation.MyWallet)
                        },
                        navigateToSettings = {
                            backStack.add(ProfileNavigation.Settings)
                        },
                    )
                }

                ProfileNavigation.Settings -> NavEntry(destination) {
                    val viewModel: SettingsViewModel = koinInject()
                    SettingsScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        logout = logout,
                        errors = viewModel.errors,
                        action = viewModel.action,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    )
                }

                ProfileNavigation.MyCoupons -> NavEntry(destination) {
                    val viewModel: MyCouponsViewModel = koinInject()
                    MyCouponsScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                ProfileNavigation.MyWallet -> NavEntry(destination) {
                    /*val viewModel: MyWalletViewModel = koinInject()
                    MyWalletScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }*/
                }

                ProfileNavigation.MyOrders -> NavEntry(destination) {
                    val viewModel: MyOrdersViewModel = koinInject()
                    MyOrdersScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                ProfileNavigation.PaymentMethod -> NavEntry(destination) {
                    val viewModel: PaymentMethodViewModel = koinInject()
                    PaymentMethodScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                ProfileNavigation.EditProfile -> NavEntry(destination) {
                    val viewModel: EditProfileViewModel = koinInject()
                    EditProfileScreen(
                        state = viewModel.state.value,
                        errors = viewModel.errors,
                        events = viewModel::onTriggerEvent,
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                ProfileNavigation.Address -> NavEntry(destination) {
                    val viewModel: AddressViewModel = koinInject()
                    AddressScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        navigateToAddAddress = {
                            backStack.add(ProfileNavigation.AddAddress)
                        }
                    ) {
                        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                    }
                }

                ProfileNavigation.AddAddress -> NavEntry(destination) {
                    AddAddressScreen(
                        context = context,
                        errors = addressViewModel.errors,
                        state = addressViewModel.state.value,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        navigateToAddInformation = {
                            backStack.add(ProfileNavigation.AddAddressInformation)
                        },
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    )
                }

                ProfileNavigation.AddAddressInformation -> NavEntry(
                    key = destination,
                ) {
                    AddAddressInformationScreen(
                        errors = addressViewModel.errors,
                        state = addressViewModel.state.value,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        },
                    )
                }
            }
        }
    )
}