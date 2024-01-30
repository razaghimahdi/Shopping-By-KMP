package presentation.ui.main.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import business.domain.main.Address
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.AddAddressDialog
import presentation.component.CircleButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_12dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.TextWithIcon
import presentation.theme.BorderColor
import presentation.ui.main.address.view_model.AddressEvent
import presentation.ui.main.address.view_model.AddressState


@Composable
fun AddressScreen(state: AddressState, events: (AddressEvent) -> Unit, popup: () -> Unit) {

    if (state.addAddressDialogState == UIComponentState.Show) {
        AddAddressDialog(onDismissRequest = {
            events(AddressEvent.OnUpdateAddAddressDialogState(UIComponentState.Hide))
        },
            onExecute = { address,country, city, state, zipCode ->
                events(
                    AddressEvent.AddAddress(
                        country = country,
                        address = address,
                        city = city,
                        state = state,
                        zipCode = zipCode
                    )
                )
            })
    }

    DefaultScreenUI(queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(AddressEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(AddressEvent.OnRetryNetwork) }) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(imageVector = Icons.Filled.ArrowBack, onClick = { popup() })
                Text("Address", style = MaterialTheme.typography.titleLarge)
                CircleButton(
                    imageVector = Icons.Filled.Add,
                    onClick = { events(AddressEvent.OnUpdateAddAddressDialogState(UIComponentState.Show)) })
            }


            if (state.addresses.isEmpty()) {
                Text(
                    "No Addresses!",
                    style = MaterialTheme.typography.titleLarge,
                    color = BorderColor,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }


            LazyColumn {
                items(state.addresses, key = { it.id }) {
                    AddressBox(address = it, modifier = Modifier.fillMaxWidth())
                }
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AddressBox(address: Address, modifier: Modifier) {
    Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.height(160.dp).fillMaxWidth().padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    address.address,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    painterResource("delete.xml"),
                    null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer_12dp()

            TextWithIcon(text = address.country, icon = "earth.xml")
            Spacer_4dp()
            TextWithIcon(text = address.getFullAddress(), icon ="location2.xml")
            Spacer_4dp()
            TextWithIcon(text = address.zipCode, icon ="mail.xml")


            Spacer_8dp()
            Divider()
        }
    }
}

