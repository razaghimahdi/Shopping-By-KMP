package presentation.ui.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.DrawableResource
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
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.arrow_right
import shoping_by_kmp.shared.generated.resources.coupon
import shoping_by_kmp.shared.generated.resources.location2
import shoping_by_kmp.shared.generated.resources.order
import shoping_by_kmp.shared.generated.resources.payment
import shoping_by_kmp.shared.generated.resources.profile2
import shoping_by_kmp.shared.generated.resources.setting2
import shoping_by_kmp.shared.generated.resources.warning
import org.jetbrains.compose.resources.stringResource
import shoping_by_kmp.shared.generated.resources.profile
import shoping_by_kmp.shared.generated.resources.payment_methods
import shoping_by_kmp.shared.generated.resources.edit_profile
import shoping_by_kmp.shared.generated.resources.my_coupons
import shoping_by_kmp.shared.generated.resources.manage_address
import shoping_by_kmp.shared.generated.resources.settings
import shoping_by_kmp.shared.generated.resources.help_center
import shoping_by_kmp.shared.generated.resources.my_orders

@Composable
fun ProfileScreen(
    state: ProfileState,
    events: (ProfileEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToAddress: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToPaymentMethod: () -> Unit,
    navigateToMyOrders: () -> Unit,
    navigateToMyCoupons: () -> Unit,
    navigateToMyWallet: () -> Unit,
    navigateToSettings: () -> Unit,
) {

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(ProfileEvent.OnRetryNetwork) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer_16dp()

            Text(stringResource(Res.string.profile), style = MaterialTheme.typography.titleLarge)

            Spacer_16dp()

            CircleImage(
                image = state.profile.profileUrl,
                modifier = Modifier.size(120.dp)
            )

            Spacer_16dp()

            Text(state.profile.name, style = MaterialTheme.typography.headlineMedium)

            Spacer_32dp()

            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileItemBox(
                    title = stringResource(Res.string.edit_profile),
                    image = Res.drawable.profile2
                ) {
                    navigateToEditProfile()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.manage_address),
                    image = Res.drawable.location2
                ) { navigateToAddress() }
                ProfileItemBox(
                    title = stringResource(Res.string.payment_methods),
                    image = Res.drawable.payment
                ) {
                    navigateToPaymentMethod()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.my_orders),
                    image = Res.drawable.order
                ) {
                    navigateToMyOrders()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.my_coupons),
                    image = Res.drawable.coupon
                ) {
                    navigateToMyCoupons()
                }
                /*ProfileItemBox(title = "My Wallet", image = "wallet.xml") {
                    navigateToMyWallet()
                }*/
                ProfileItemBox(
                    title = stringResource(Res.string.settings),
                    image = Res.drawable.setting2
                ) {
                    navigateToSettings()
                }
                ProfileItemBox(
                    title = stringResource(Res.string.help_center),
                    image = Res.drawable.warning,
                    isLastItem = true
                ) {}
            }

        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ProfileItemBox(
    title: String,
    image: DrawableResource,
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
                painterResource(Res.drawable.arrow_right),
                null,
                tint = MaterialTheme.colorScheme.primary.copy(alpha = .7f),
                modifier = Modifier.size(30.dp)
            )
        }
        if (!isLastItem) {
            Spacer_12dp()
            HorizontalDivider()
            Spacer_12dp()
        }
    }

}