package presentation.ui.main.cart

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import common.Context
import org.koin.compose.koinInject
import presentation.navigation.CartNavigation
import presentation.ui.main.add_address.AddAddressInformationScreen
import presentation.ui.main.add_address.AddAddressScreen
import presentation.ui.main.add_address.view_model.AddAddressViewModel
import presentation.ui.main.address.AddressScreen
import presentation.ui.main.address.view_model.AddressViewModel
import presentation.ui.main.cart.view_model.CartViewModel
import presentation.ui.main.checkout.CheckoutScreen
import presentation.ui.main.checkout.view_model.CheckoutViewModel
import presentation.ui.main.detail.DetailNav

@Composable
fun CartNav(context: Context?) {
    val backStack = remember { mutableStateListOf<CartNavigation>(CartNavigation.Cart) }

    val addressViewModel: AddAddressViewModel = koinInject()



    NavDisplay(
        backStack = backStack, onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                CartNavigation.Cart -> NavEntry(key) {

                    val viewModel: CartViewModel = koinInject()
                    CartScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        navigateToDetail = {
                            backStack.add(CartNavigation.Detail(it))
                        }, navigateToCheckout = {
                            backStack.add(CartNavigation.Checkout)
                        })
                }

                is CartNavigation.Checkout -> NavEntry(key) {

                    val viewModel: CheckoutViewModel = koinInject()
                    CheckoutScreen(
                        errors = viewModel.errors,
                        action = viewModel.action,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        navigateToAddress = {
                            backStack.add(CartNavigation.Address)
                        },
                        popup = { backStack.removeLastOrNull() },
                    )
                }

                is CartNavigation.Address -> NavEntry(key) {
                    val viewModel: AddressViewModel = koinInject()
                    AddressScreen(
                        errors = viewModel.errors,
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        navigateToAddAddress = {
                            backStack.add(CartNavigation.AddAddress)
                        },
                        popup = { backStack.removeLastOrNull() },
                    )
                }

                is CartNavigation.Detail -> NavEntry(key) { entry->
                    val id = key.id
                    DetailNav(id) {
                        backStack.removeLastOrNull()
                    }
                }

                is CartNavigation.AddAddress -> NavEntry(key) {
                    AddAddressScreen(
                        context = context,
                        errors = addressViewModel.errors,
                        state = addressViewModel.state.value,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        navigateToAddInformation = {
                            backStack.add(CartNavigation.AddAddressInformation)
                        },
                        popup = { backStack.removeLastOrNull() },
                    )
                }

                is CartNavigation.AddAddressInformation -> NavEntry(
                    key,
                    metadata = NavDisplay.transitionSpec {
                        slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = tween(1000)
                        ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                    } + NavDisplay.popTransitionSpec {
                        EnterTransition.None togetherWith
                                slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(1000)
                                )
                    } + NavDisplay.predictivePopTransitionSpec {
                        EnterTransition.None togetherWith
                                slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(1000)
                                )
                    },
                ) {
                    AddAddressInformationScreen(
                        errors = addressViewModel.errors,
                        state = addressViewModel.state.value,
                        action = addressViewModel.action,
                        events = addressViewModel::onTriggerEvent,
                        popup = { backStack.removeLastOrNull() },
                    )
                }
            }
        })


}
