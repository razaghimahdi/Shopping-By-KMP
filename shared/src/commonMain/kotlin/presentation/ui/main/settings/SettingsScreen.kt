package presentation.ui.main.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.CircleButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.theme.BorderColor
import presentation.ui.main.settings.view_model.SettingsEvent
import presentation.ui.main.settings.view_model.SettingsState


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SettingsScreen(
    state: SettingsState,
    events: (SettingsEvent) -> Unit,
    popup: () -> Unit,
    logout: () -> Unit
) {
    LaunchedEffect(key1 = state.logout) {
        if (state.logout) {
            logout()
        }
    }

    DefaultScreenUI(queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(SettingsEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(SettingsEvent.OnRetryNetwork) }) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(imageVector = Icons.Filled.ArrowBack, onClick = { popup() })
                Text("Setting", style = MaterialTheme.typography.titleLarge)
                Spacer_8dp()
            }

            Spacer_32dp()

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).noRippleClickable {
                events(SettingsEvent.Logout)
            }, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource("exit.xml"),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
                Spacer_8dp()
                Text(
                    "Logout",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    painter = painterResource("arrow_right.xml"),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
            }
            Divider(color = BorderColor)
        }
    }
}