package presentation.ui.main.my_coupons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import business.domain.main.Coupons
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.theme.BorderColor
import presentation.theme.grey_050
import presentation.ui.main.my_coupons.view_model.MyCouponsEvent
import presentation.ui.main.my_coupons.view_model.MyCouponsState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.offer


@Composable
fun MyCouponsScreen(state: MyCouponsState, events: (MyCouponsEvent) -> Unit, popup: () -> Unit) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(MyCouponsEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(MyCouponsEvent.OnRetryNetwork) },
        titleToolbar = "My Coupons",
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {

            Spacer_32dp()

            Text("Best offer for you", style = MaterialTheme.typography.titleLarge)
            Spacer_8dp()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(state.couponsList) {
                    Coupon(it) {
                        clipboardManager.setText(AnnotatedString(it.code))
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Coupon(coupons: Coupons, onExecuteCopyCode: () -> Unit) {

    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp)
                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(coupons.title, style = MaterialTheme.typography.titleLarge)
                Text(coupons.desc, style = MaterialTheme.typography.bodyMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.offer),
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        "Get ${coupons.offPercent}% OFF",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }


            Box(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
                    .background(grey_050).noRippleClickable {
                        onExecuteCopyCode()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "COPY CODE",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
            }


        }
    }
}

