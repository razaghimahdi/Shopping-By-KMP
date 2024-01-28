package presentation.ui.main.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import presentation.component.CircleButton
import presentation.component.CircleImage
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.theme.DefaultTextFieldTheme
import presentation.ui.main.address.view_model.AddressEvent
import presentation.ui.main.edit_profile.view_model.EditProfileEvent
import presentation.ui.main.edit_profile.view_model.EditProfileState
import presentation.ui.splash.view_model.LoginEvent


@Composable
fun EditProfileScreen(
    state: EditProfileState,
    events: (EditProfileEvent) -> Unit,
    popup: () -> Unit
) {

    DefaultScreenUI(queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(EditProfileEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(EditProfileEvent.OnRetryNetwork) }) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(imageVector = Icons.Filled.ArrowBack, onClick = { popup() })
                Text("Edit Profile", style = MaterialTheme.typography.titleLarge)
                Spacer_4dp()
            }
            Spacer_32dp()

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircleImage(state.image, modifier = Modifier.size(80.dp))
                Spacer_8dp()
                DefaultButton(text = "Select") {}
            }

            Spacer_32dp()

            TextField(
                value = state.name,
                onValueChange = {
                    if (it.length < 32) {
                        events(EditProfileEvent.OnUpdateName(it))
                    }
                },
                label={
                      Text("Name")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )

            Spacer_16dp()

            TextField(
                value = state.email,
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                label={
                    Text("Email")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )

            Spacer_32dp()

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE),
                progressBarState = state.progressBarState,
                text = "Submit"
            ) {

            }

        }
    }
}
