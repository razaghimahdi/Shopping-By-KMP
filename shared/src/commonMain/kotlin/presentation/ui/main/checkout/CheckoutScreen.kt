package presentation.ui.main.checkout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.SelectShippingDialog
import presentation.component.Spacer_12dp
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.navigation.SplashNavigation
import presentation.theme.BorderColor
import presentation.theme.DefaultCardColorsTheme
import presentation.ui.main.checkout.view_model.CheckoutAction
import presentation.ui.main.checkout.view_model.CheckoutEvent
import presentation.ui.main.checkout.view_model.CheckoutState
import presentation.ui.splash.view_model.LoginAction
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.change
import shoping_by_kmp.shared.generated.resources.checkout
import shoping_by_kmp.shared.generated.resources.choose_shipping_type
import shoping_by_kmp.shared.generated.resources.home
import shoping_by_kmp.shared.generated.resources.location2
import shoping_by_kmp.shared.generated.resources.shipping
import shoping_by_kmp.shared.generated.resources.shipping_address
import shoping_by_kmp.shared.generated.resources.shipping_cost
import shoping_by_kmp.shared.generated.resources.submit
import shoping_by_kmp.shared.generated.resources.total_cost


@Composable
fun CheckoutScreen(
    state: CheckoutState,
    errors: Flow<UIComponent>,
    action: Flow<CheckoutAction>,
    events: (CheckoutEvent) -> Unit,
    navigateToAddress: () -> Unit,
    popup: () -> Unit
) {

    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                CheckoutAction.Navigation.PopUp -> {
                    popup()
                }
            }
        }.collect {}
    }


    if (state.selectShippingDialogState == UIComponentState.Show) {
        SelectShippingDialog(state = state, events = events)
    }


    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CheckoutEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.checkout),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp).align(Alignment.TopCenter)) {


                Spacer_32dp()

                Text(
                    stringResource(Res.string.shipping_address),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer_12dp()
                ShippingBox(
                    title = stringResource(Res.string.home),
                    image = Res.drawable.location2,
                    detail = state.selectedAddress.getShippingAddress()
                ) {
                    navigateToAddress()
                }

                Spacer_16dp()
                HorizontalDivider(color = BorderColor)
                Spacer_16dp()

                Text(
                    stringResource(Res.string.choose_shipping_type),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer_12dp()
                ShippingBox(
                    title = state.selectedShipping.title,
                    image = Res.drawable.shipping,
                    detail = state.selectedShipping.getEstimatedDay(),
                ) {
                    events(CheckoutEvent.OnUpdateSelectShippingDialogState(UIComponentState.Show))
                }


            }

            Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                CheckoutButtonBox(
                    "$ ${state.totalCost}",
                    "$ ${state.selectedShipping.price}",
                    selectedAddress = state.selectedAddress,
                ) {
                    events(CheckoutEvent.BuyProduct)
                }
            }
        }

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CheckoutButtonBox(
    totalCost: String,
    shippingCost: String,
    selectedAddress: Address,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = DefaultCardColorsTheme(),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(Res.string.shipping_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(shippingCost, style = MaterialTheme.typography.titleLarge)
            }
            Spacer_8dp()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(Res.string.total_cost),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(totalCost, style = MaterialTheme.typography.titleLarge)
            }

            Spacer_16dp()
            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE),
                text = stringResource(Res.string.submit),
                enabled = selectedAddress != Address()
            ) {
                onClick()
            }
        }
    }
}

@Composable
fun ShippingBox(title: String, image: DrawableResource, detail: String, onClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(image),
            null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer_8dp()
        Column(modifier = Modifier.fillMaxWidth(.7f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(detail, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer_8dp()
        Box(modifier = Modifier.wrapContentHeight(), contentAlignment = Alignment.CenterEnd) {
            Box(
                modifier = Modifier.border(
                    1.dp,
                    color = BorderColor,
                    MaterialTheme.shapes.medium
                ).noRippleClickable { onClick() }
            ) {
                Text(
                    stringResource(Res.string.change),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
