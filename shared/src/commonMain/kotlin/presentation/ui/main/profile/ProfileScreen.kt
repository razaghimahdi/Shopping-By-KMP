package presentation.ui.main.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.CircleImage
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_12dp
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.ui.main.profile.view_model.ProfileEvent
import presentation.ui.main.profile.view_model.ProfileState

@Composable
fun ProfileScreen(state: ProfileState, events: (ProfileEvent) -> Unit) {

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(ProfileEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(ProfileEvent.OnRetryNetwork) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer_16dp()

            Text("Profile", style = MaterialTheme.typography.titleLarge)

            Spacer_16dp()

            CircleImage(
                image = state.profile.profileUrl,
                modifier = Modifier.size(75.dp)
            )

            Spacer_16dp()

            Text(state.profile.name, style = MaterialTheme.typography.headlineMedium)

            Spacer_32dp()

            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileItemBox(title = "Edit profile", image = "profile2.xml") {}
                ProfileItemBox(title = "Manage Address", image = "location2.xml") {}
                ProfileItemBox(title = "Payment Methods", image = "payment.xml") {}
                ProfileItemBox(title = "My Orders", image = "order.xml") {}
                ProfileItemBox(title = "My Coupons", image = "coupon.xml") {}
                ProfileItemBox(title = "My Wallet", image = "wallet.xml") {}
                ProfileItemBox(title = "Settings", image = "setting2.xml") {}
                ProfileItemBox(title = "Help Center", image = "warning.xml", isLastItem = true) {}
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProfileItemBox(
    title: String,
    image: String,
    isLastItem: Boolean = false,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            .noRippleClickable { onClick() }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painterResource(image),
                    null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(35.dp)
                )
                Spacer_8dp()
                Text(title, style = MaterialTheme.typography.bodyLarge)
            }

            Icon(
                painterResource("arrow_right.xml"),
                null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = .7f),
                modifier = Modifier.size(30.dp)
            )
        }
        if (!isLastItem) {
            Spacer_12dp()
            Divider()
            Spacer_12dp()
        }
    }

}