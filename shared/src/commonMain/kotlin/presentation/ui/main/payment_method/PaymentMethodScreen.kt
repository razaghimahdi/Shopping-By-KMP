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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
import presentation.component.CircleButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.navigation.ProfileNavigation
import presentation.theme.BorderColor
import presentation.ui.main.address.view_model.AddressEvent
import presentation.ui.main.payment_method.view_model.PaymentMethodEvent
import presentation.ui.main.payment_method.view_model.PaymentMethodState


@OptIn(ExperimentalResourceApi::class)
@Composable
fun PaymentMethodScreen(
    state: PaymentMethodState,
    events: (PaymentMethodEvent) -> Unit,
    popup: () -> Unit
) {
    DefaultScreenUI(queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(PaymentMethodEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(PaymentMethodEvent.OnRetryNetwork) }) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(imageVector = Icons.Filled.ArrowBack, onClick = { popup() })
                Text("Payment Method", style = MaterialTheme.typography.titleLarge)
                Spacer_8dp()
            }

            Spacer_32dp()

            Text("Cash", style = MaterialTheme.typography.titleMedium)
            Spacer_8dp()
            ChipsCardBox(
                text = "Cash",
                image = "cash.xml",
                isSelected = state.selectedPaymentMethod == 0,
                onSelectExecute = { events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(0)) })



            Spacer_16dp()

            Text("Wallet", style = MaterialTheme.typography.titleMedium)
            Spacer_8dp()
            ChipsCardBox(
                text = "Wallet",
                image = "wallet.xml",
                isSelected = state.selectedPaymentMethod == 1,
                onSelectExecute = { events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(1)) })



            Spacer_16dp()

            Text("More Payment Options", style = MaterialTheme.typography.titleMedium)
            Spacer_8dp()


            Card(
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
                                painter = painterResource("paypal.xml"),
                                null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Paypal", style = MaterialTheme.typography.bodyLarge)
                        }

                        Switch(checked = state.selectedPaymentMethod == 2, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(2))
                        })

                    }
                    Divider(color = BorderColor)
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
                                painter = painterResource("apple.xml"), null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Apple Pay", style = MaterialTheme.typography.bodyLarge)
                        }

                        Switch(checked = state.selectedPaymentMethod == 3, onCheckedChange = {
                            events(PaymentMethodEvent.OnUpdateSelectedPaymentMethod(3))
                        })

                    }
                    Divider(color = BorderColor)
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
                                painter = painterResource("google.xml"), null,
                                modifier = Modifier.size(24.dp)
                            )
                            Text("Google Pay", style = MaterialTheme.typography.bodyLarge)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
private fun ChipsCardBox(
    text: String,
    image: String,
    isSelected: Boolean,
    onSelectExecute: () -> Unit
) {


    Card(
        onClick = onSelectExecute,
        modifier = Modifier,
        border = BorderStroke(1.dp, BorderColor),
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
