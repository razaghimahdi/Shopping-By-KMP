package com.razzaghi.shopingbykmp.presentation.ui.main.add_address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.presentation.util.MapComponent
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.stringResource
import com.razzaghi.shopingbykmp.presentation.component.DefaultScreenUI
import com.razzaghi.shopingbykmp.presentation.component.IconButton
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressAction
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model.AddAddressState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.add_new_address
import shoping_by_kmp.shared.generated.resources.confirm


@Composable
fun AddAddressScreen(
    state: AddAddressState,
    errors: Flow<UIComponent>,
    action: Flow<AddAddressAction>,
    events: (AddAddressEvent) -> Unit,
    navigateToAddInformation: () -> Unit,
    popup: () -> Unit,
) {


    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        titleToolbar = stringResource(Res.string.add_new_address),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {
            Box(modifier = Modifier.fillMaxSize()) {
                MapComponent( onLatitude = {
                    println("AppDebug AddAddressScreen onLatitude:" + it)
                    events(AddAddressEvent.OnUpdateLatitude(it))
                }, onLongitude = {
                    println("AppDebug AddAddressScreen onLongitude:" + it)
                    events(AddAddressEvent.OnUpdateLongitude(it))
                })
                IconButton(
                    icon = Icons.Default.Done,
                    text = stringResource(Res.string.confirm),
                    modifier = Modifier.padding(16.dp).align(
                        Alignment.BottomStart
                    )
                ) {
                    navigateToAddInformation()
                }
            }
    }
}