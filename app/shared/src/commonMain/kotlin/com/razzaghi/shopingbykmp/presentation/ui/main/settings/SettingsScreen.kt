package com.razzaghi.shopingbykmp.presentation.ui.main.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaghi.shopingbykmp.business.core.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import com.razzaghi.shopingbykmp.presentation.component.DefaultScreenUI
import com.razzaghi.shopingbykmp.presentation.component.Spacer_32dp
import com.razzaghi.shopingbykmp.presentation.component.Spacer_8dp
import com.razzaghi.shopingbykmp.presentation.component.noRippleClickable
import com.razzaghi.shopingbykmp.presentation.theme.BorderColor
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model.SettingsAction
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model.SettingsEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model.SettingsState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.arrow_right
import shoping_by_kmp.shared.generated.resources.exit
import shoping_by_kmp.shared.generated.resources.logout
import shoping_by_kmp.shared.generated.resources.setting


@Composable
fun SettingsScreen(
    state: SettingsState,
    events: (SettingsEvent) -> Unit,
    errors: Flow<UIComponent>,
    action: Flow<SettingsAction>,
    popup: () -> Unit,
    logout: () -> Unit
) {

    LaunchedEffect(key1 = action) {
        action.onEach { effect ->
            when (effect) {
                SettingsAction.Navigation.PopUp -> {
                    logout()
                }
            }
        }.collect {}
    }


    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(SettingsEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.setting),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


            Spacer_32dp()

            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).noRippleClickable {
                events(SettingsEvent.Logout)
            }, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.exit),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
                Spacer_8dp()
                Text(
                    stringResource(Res.string.logout),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(.9f)
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_right),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp),
                )
            }
            HorizontalDivider(color = BorderColor)
        }
    }
}