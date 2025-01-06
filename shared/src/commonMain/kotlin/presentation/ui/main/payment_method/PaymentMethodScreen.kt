package presentation.ui.main.payment_method

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.theme.BorderColor
import presentation.theme.DefaultCardColorsTheme
import presentation.ui.main.payment_method.view_model.PaymentMethodEvent
import presentation.ui.main.payment_method.view_model.PaymentMethodState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.apple
import shoping_by_kmp.shared.generated.resources.apple_pay
import shoping_by_kmp.shared.generated.resources.cash
import shoping_by_kmp.shared.generated.resources.google
import shoping_by_kmp.shared.generated.resources.google_pay
import shoping_by_kmp.shared.generated.resources.more_payment_options
import shoping_by_kmp.shared.generated.resources.payment_method
import shoping_by_kmp.shared.generated.resources.paypal
import shoping_by_kmp.shared.generated.resources.wallet


@Composable
fun PaymentMethodScreen(
    errors: Flow<UIComponent>,
    state: PaymentMethodState,
    events: (PaymentMethodEvent) -> Unit,
    popup: () -> Unit
) {
    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(PaymentMethodEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.payment_method),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


            Spacer_32dp()

            Text(stringResource(Res.string.cash), style = MaterialTheme.typography.titleMedium)
            Spacer_8dp()
            ChipsCardBox(
                text = stringResource(Res.string.cash),
                image = Res.drawable.cash,
                isSelected = state.selectedPaymentMethod == 0,
                onSelectExecute = { events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(0)) })



            Spacer_16dp()

            Text(stringResource(Res.string.wallet), style = MaterialTheme.typography.titleMedium)
            Spacer_8dp()
            ChipsCardBox(
                text = stringResource(Res.string.wallet),
                image = Res.drawable.wallet,
                isSelected = state.selectedPaymentMethod == 1,
                onSelectExecute = { events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(1)) })



            Spacer_16dp()

            Text(
                stringResource(Res.string.more_payment_options),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer_8dp()


            Card(
                colors = DefaultCardColorsTheme(),
                modifier = Modifier,
                border = BorderStroke(1.dp, BorderColor),
                shape = MaterialTheme.shapes.small
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(2))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.paypal),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                stringResource(Res.string.paypal),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Switch(checked = state.selectedPaymentMethod == 2, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(2))
                        })

                    }
                    HorizontalDivider(color = BorderColor)
                    Row(
                        modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(3))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.apple), null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                stringResource(Res.string.apple_pay),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Switch(checked = state.selectedPaymentMethod == 3, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(3))
                        })

                    }
                    HorizontalDivider(color = BorderColor)
                    Row(
                        modifier = Modifier.padding(12.dp).fillMaxWidth().noRippleClickable {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(4))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.google), null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                stringResource(Res.string.google_pay),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                        Switch(checked = state.selectedPaymentMethod == 4, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(4))
                        })

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ChipsCardBox(
    text: String,
    image: DrawableResource,
    isSelected: Boolean,
    onSelectExecute: () -> Unit
) {


    Card(
        onClick = onSelectExecute,
        modifier = Modifier,
        border = BorderStroke(1.dp, BorderColor),
        colors = DefaultCardColorsTheme(),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(image), null,
                    modifier = Modifier.size(24.dp)
                )
                Text(text, style = MaterialTheme.typography.bodyLarge)
            }

            Switch(checked = isSelected, onCheckedChange = { onSelectExecute() })

        }
    }


}
