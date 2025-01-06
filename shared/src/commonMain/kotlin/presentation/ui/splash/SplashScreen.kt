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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import presentation.component.MultiStateAnimationCircleFilledCanvas
import presentation.component.Spacer_32dp
import presentation.theme.grey_700
import presentation.theme.splashBackground
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.discover
import shoping_by_kmp.shared.generated.resources.enjoy
import shoping_by_kmp.shared.generated.resources.getting_start
import shoping_by_kmp.shared.generated.resources.live_your_perfect
import shoping_by_kmp.shared.generated.resources.shop
import shoping_by_kmp.shared.generated.resources.splash_title

@Composable
internal fun SplashScreen() {


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
                stringResource(Res.string.shop), style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
            Spacer_32dp()
            Text(
                stringResource(Res.string.discover), style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
            Spacer_32dp()
            Text(
                stringResource(Res.string.enjoy), style = MaterialTheme.typography.displayLarge,
                color = grey_700,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(Res.string.live_your_perfect),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
            Spacer_32dp()
            Text(
                stringResource(Res.string.splash_title),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 32.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Spacer_32dp()
            Text(
                stringResource(Res.string.getting_start),
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}