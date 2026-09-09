package presentation.ui.main.add_address

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import common.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.resources.stringResource
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.theme.BorderColor
import presentation.theme.DefaultTextFieldTheme
import presentation.ui.main.add_address.view_model.AddAddressAction
import presentation.ui.main.add_address.view_model.AddAddressEvent
import presentation.ui.main.add_address.view_model.AddAddressState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.add_new_address
import shoping_by_kmp.shared.generated.resources.country
import shoping_by_kmp.shared.generated.resources.submit
import shoping_by_kmp.shared.generated.resources.state
import shoping_by_kmp.shared.generated.resources.city
import shoping_by_kmp.shared.generated.resources.address_dot
import shoping_by_kmp.shared.generated.resources.zip_code


@Composable
fun AddAddressInformationScreen(
    state: AddAddressState,
    errors: Flow<UIComponent>,
    action: Flow<AddAddressAction>,
    events: (AddAddressEvent) -> Unit,
    popup: () -> Unit,
) {

    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                AddAddressAction.Navigation.PopUp -> {
                    popup()
                }
            }
        }.collect {}
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        titleToolbar = stringResource(Res.string.add_new_address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState()),
        ) {


            Text(
                stringResource(Res.string.country),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = state.country, onValueChange = {events(AddAddressEvent.OnUpdateCountry(it)) },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer_8dp()
            Text(
                stringResource(Res.string.state),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = state.state, onValueChange = { events(AddAddressEvent.OnUpdateState(it)) },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer_8dp()
            Text(
                stringResource(Res.string.city),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = state.city, onValueChange = { events(AddAddressEvent.OnUpdateCity(it)) },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer_8dp()
            Text(
                stringResource(Res.string.address_dot),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = state.address, onValueChange = { events(AddAddressEvent.OnUpdateAddress(it)) },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            Spacer_8dp()
            Text(
                stringResource(Res.string.zip_code),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = state.zipCode, onValueChange = { events(AddAddressEvent.OnUpdateZipCode(it)) },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                ),
            )




            Spacer_16dp()

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE),
                text = stringResource(Res.string.submit)
            ) {
                events(AddAddressEvent.AddAddress)
            }

            Spacer_16dp()
        }

    }
}



