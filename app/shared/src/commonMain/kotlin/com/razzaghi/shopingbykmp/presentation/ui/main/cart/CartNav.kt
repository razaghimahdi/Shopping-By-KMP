package com.razzaghi.shopingbykmp.presentation.ui.main.cart

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
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import com.razzaghi.shopingbykmp.presentation.navigation.CartNavigation
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.AddAddressInformationScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.AddAddressScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.address.AddressScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.address.view_model.AddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.cart.view_model.CartViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.checkout.CheckoutScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model.CheckoutViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.detail.DetailNav

@Composable
fun CartNav() {
    val navigator = rememberNavController()
    val addressViewModel: AddAddressViewModel = koinInject()
    NavHost(
        startDestination = CartNavigation.Cart,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<CartNavigation.Cart> {
            val viewModel: CartViewModel = koinInject()
            CartScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                navigateToDetail = {
                    navigator.navigate(CartNavigation.Detail(it))
                }, navigateToCheckout = {
                    navigator.navigate(CartNavigation.Checkout)
                })
        }
        composable<CartNavigation.Checkout> {
            val viewModel: CheckoutViewModel = koinInject()
            CheckoutScreen(
                errors = viewModel.errors,
                action = viewModel.action,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToAddress = {
                    navigator.navigate(CartNavigation.Address)
                },
                popup = { navigator.popBackStack() },
            )
        }
        composable<CartNavigation.Address> {
            val viewModel: AddressViewModel = koinInject()
            AddressScreen(
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToAddAddress = { navigator.navigate(CartNavigation.AddAddress) },
                popup = { navigator.popBackStack() },
            )
        }
        composable<CartNavigation.Detail> { backStackEntry ->
            val argument = backStackEntry.toRoute<CartNavigation.Detail>()
            val id = argument.id
            DetailNav(id) {
                navigator.popBackStack()
            }
        }
        composable<CartNavigation.AddAddress> {
            AddAddressScreen(
                errors = addressViewModel.errors,
                state = addressViewModel.state.value,
                action = addressViewModel.action,
                events = addressViewModel::onTriggerEvent,
                navigateToAddInformation = { navigator.navigate(CartNavigation.AddAddressInformation) },
                popup = { navigator.popBackStack() },
            )
        }
        composable<CartNavigation.AddAddressInformation> (
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
            }){
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
