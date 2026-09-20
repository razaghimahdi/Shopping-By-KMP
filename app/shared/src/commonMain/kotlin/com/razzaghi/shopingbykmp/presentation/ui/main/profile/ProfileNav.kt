package com.razzaghi.shopingbykmp.presentation.ui.main.profile

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import com.razzaghi.shopingbykmp.presentation.navigation.ProfileNavigation
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.AddAddressInformationScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.AddAddressScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.address.AddressScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.address.view_model.AddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.edit_profile.EditProfileScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.edit_profile.view_model.EditProfileViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.my_coupons.MyCouponsScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.my_coupons.view_model.MyCouponsViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.my_orders.MyOrdersScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.my_orders.view_model.MyOrdersViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.payment_method.PaymentMethodScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.payment_method.view_model.PaymentMethodViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.profile.view_model.ProfileViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.SettingsScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileNav(logout: () -> Unit) {
    val addressViewModel = koinViewModel<AddAddressViewModel>()
    val navigator = rememberNavController()
    NavHost(
        startDestination = ProfileNavigation.Profile,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<ProfileNavigation.Profile> {
            val viewModel: ProfileViewModel = koinInject()
            ProfileScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                navigateToAddress = {
                    navigator.navigate(ProfileNavigation.Address)
                },
                navigateToEditProfile = {
                    navigator.navigate(ProfileNavigation.EditProfile)
                },
                navigateToPaymentMethod = {
                    navigator.navigate(ProfileNavigation.PaymentMethod)
                },
                navigateToMyOrders = {
                    navigator.navigate(ProfileNavigation.MyOrders)
                },
                navigateToMyCoupons = {
                    navigator.navigate(ProfileNavigation.MyCoupons)
                },
                navigateToMyWallet = {
                    navigator.navigate(ProfileNavigation.MyWallet)
                },
                navigateToSettings = {
                    navigator.navigate(ProfileNavigation.Settings)
                },
            )
        }
        composable<ProfileNavigation.Settings> {
            val viewModel = koinViewModel<SettingsViewModel>()

            SettingsScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                logout = logout,
                errors = viewModel.errors,
                action = viewModel.action,
                popup = {
                    navigator.popBackStack()
                },
            )
        }
        composable<ProfileNavigation.MyCoupons> {
            val viewModel = koinViewModel<MyCouponsViewModel>()
            MyCouponsScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        composable<ProfileNavigation.MyWallet> {
            /*val viewModel: MyWalletViewModel = koinInject()
            MyWalletScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }*/
        }
        composable<ProfileNavigation.MyOrders> {
            val viewModel = koinViewModel<MyOrdersViewModel>()
            MyOrdersScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        composable<ProfileNavigation.PaymentMethod> {
            val viewModel = koinViewModel<PaymentMethodViewModel>()
            PaymentMethodScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        composable<ProfileNavigation.EditProfile> {
            val viewModel = koinViewModel<EditProfileViewModel>()
            EditProfileScreen(
                state = viewModel.state.value,
                errors = viewModel.errors,
                events = viewModel::onTriggerEvent,
            ) {
                navigator.popBackStack()
            }
        }
        composable<ProfileNavigation.Address> {
            val viewModel = koinViewModel<AddressViewModel>()
            AddressScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToAddAddress = {
                    navigator.navigate(ProfileNavigation.AddAddress)
                }
            ) {
                navigator.popBackStack()
            }
        }
        composable<ProfileNavigation.AddAddress> {
            AddAddressScreen(
                
                errors = addressViewModel.errors,
                state = addressViewModel.state.value,
                action = addressViewModel.action,
                events = addressViewModel::onTriggerEvent,
                navigateToAddInformation = { navigator.navigate(ProfileNavigation.AddAddressInformation) },
                popup = { navigator.popBackStack() },
            )
        }
        composable<ProfileNavigation.AddAddressInformation>(
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { 1000 },
                    animationSpec = tween(500)
                ) + fadeIn(tween(300))
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { 1000 },
                    animationSpec = tween(750)
                ) + fadeOut(tween(500))
            }) {
            AddAddressInformationScreen(
                errors = addressViewModel.errors,
                state = addressViewModel.state.value,
                action = addressViewModel.action,
                events = addressViewModel::onTriggerEvent,
                popup = { navigator.popBackStack() },
            )
        }
    }
}

