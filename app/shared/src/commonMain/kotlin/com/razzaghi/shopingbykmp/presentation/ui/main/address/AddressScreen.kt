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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_12dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.TextWithIcon
import presentation.theme.BorderColor
import presentation.ui.main.address.view_model.AddressEvent
import presentation.ui.main.address.view_model.AddressState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.address
import shoping_by_kmp.shared.generated.resources.delete
import shoping_by_kmp.shared.generated.resources.earth
import shoping_by_kmp.shared.generated.resources.location2
import shoping_by_kmp.shared.generated.resources.mail
import shoping_by_kmp.shared.generated.resources.no_address


@Composable
fun AddressScreen(
    state: AddressState,
    errors: Flow<UIComponent>,
    events: (AddressEvent) -> Unit,
    navigateToAddAddress: () -> Unit,
    popup: () -> Unit,
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(AddressEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup,
        endIconToolbar = Icons.Filled.Add,
        onClickEndIconToolbar = navigateToAddAddress) {

        Column(modifier = Modifier.fillMaxSize()) {


            if (state.addresses.isEmpty()) {
                Text(
                    stringResource(Res.string.no_address),
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
                    painterResource(Res.drawable.delete),
                    null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer_12dp()

            TextWithIcon(text = address.country, icon = Res.drawable.earth)
            Spacer_4dp()
            TextWithIcon(text = address.getFullAddress(), icon = Res.drawable.location2)
            Spacer_4dp()
            TextWithIcon(text = address.zipCode, icon = Res.drawable.mail)


            Spacer_8dp()
            HorizontalDivider()
        }
    }
}

