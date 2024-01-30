package presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import presentation.component.MultiStateAnimationCircleFilledCanvas
import presentation.component.Spacer_32dp
import presentation.theme.grey_700
import presentation.theme.splashBackground
import presentation.ui.splash.view_model.LoginEvent
import presentation.ui.splash.view_model.LoginState

@Composable
internal fun SplashScreen(
    state: LoginState,
    events: (LoginEvent) -> Unit,
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
) {



    LaunchedEffect(state.navigateToMain) {
        delay(3000L)
        if (state.navigateToMain) {
            navigateToMain()
        } else {
            navigateToLogin()
        }
    }



    Box(
        modifier = Modifier.fillMaxSize().background(splashBackground),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter),
            contentAlignment = Alignment.BottomCenter
        ) {
            MultiStateAnimationCircleFilledCanvas()
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer_32dp()
            Text(
                "Shop", style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
            Spacer_32dp()
            Text(
                "Discover", style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
            Spacer_32dp()
            Text(
                "Enjoy", style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "LIVE YOUR\nPERFECT",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
            Spacer_32dp()
            Text(
                "Smart, gorgeous & fashionable collection make you cool",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Spacer_32dp()
            Text(
                "Getting Start...",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}