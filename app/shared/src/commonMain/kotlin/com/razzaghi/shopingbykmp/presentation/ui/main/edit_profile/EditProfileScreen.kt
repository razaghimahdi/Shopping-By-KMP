package presentation.ui.main.edit_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import business.core.UIComponentState
import common.PermissionCallback
import common.PermissionStatus
import common.PermissionType
import common.createPermissionsManager
import common.rememberCameraManager
import common.rememberGalleryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.stringResource
import presentation.component.CircleImage
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.GeneralAlertDialog
import presentation.component.ImageOptionDialog
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.theme.DefaultTextFieldTheme
import presentation.ui.main.edit_profile.view_model.EditProfileEvent
import presentation.ui.main.edit_profile.view_model.EditProfileState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.age
import shoping_by_kmp.shared.generated.resources.cancel
import shoping_by_kmp.shared.generated.resources.edit_profile
import shoping_by_kmp.shared.generated.resources.email
import shoping_by_kmp.shared.generated.resources.name
import shoping_by_kmp.shared.generated.resources.permission_required_desc
import shoping_by_kmp.shared.generated.resources.permission_required_title
import shoping_by_kmp.shared.generated.resources.select
import shoping_by_kmp.shared.generated.resources.settings
import shoping_by_kmp.shared.generated.resources.submit


@Composable
fun EditProfileScreen(
    state: EditProfileState,
    events: (EditProfileEvent) -> Unit,
    errors: Flow<UIComponent>,
    popup: () -> Unit
) {


    val coroutineScope = rememberCoroutineScope()
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }

    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    events(EditProfileEvent.OnUpdatePermissionDialog(UIComponentState.Show))
                }
            }
        }


    })

    val cameraManager = rememberCameraManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            imageBitmap = bitmap
        }
    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            imageBitmap = bitmap
        }
    }
    if (state.imageOptionDialog == UIComponentState.Show) {
        ImageOptionDialog(onDismissRequest = {
            events(EditProfileEvent.OnUpdateImageOptionDialog(UIComponentState.Hide))
        }, onGalleryRequest = {
            launchGallery = true
        }, onCameraRequest = {
            launchCamera = true
        })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.AskPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.AskPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.LaunchSettings()
        launchSetting = false
    }
    if (state.permissionDialog == UIComponentState.Show) {
        GeneralAlertDialog(title = stringResource(Res.string.permission_required_title),
            message = stringResource(Res.string.permission_required_desc),
            positiveButtonText = stringResource(Res.string.settings),
            negativeButtonText = stringResource(Res.string.cancel),
            onDismissRequest = {
                events(EditProfileEvent.OnUpdatePermissionDialog(UIComponentState.Hide))
            },
            onPositiveClick = {
                launchSetting = true

            },
            onNegativeClick = {
            })

    }



    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(EditProfileEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.edit_profile),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            Spacer_32dp()

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (imageBitmap == null) {
                    CircleImage(state.image, modifier = Modifier.size(120.dp))
                } else {
                    CircleImage(imageBitmap, modifier = Modifier.size(120.dp))
                }
                Spacer_8dp()
                DefaultButton(text = stringResource(Res.string.select)) {
                    events(EditProfileEvent.OnUpdateImageOptionDialog(UIComponentState.Show))
                }
            }

            Spacer_32dp()

            TextField(
                value = state.name,
                onValueChange = {
                    if (it.length < 32) {
                        events(EditProfileEvent.OnUpdateName(it))
                    }
                },
                label = {
                    Text(stringResource(Res.string.name))
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
                value = state.age,
                onValueChange = {
                    if (it.length < 3) {
                        events(EditProfileEvent.OnUpdateAge(it))
                    }
                },
                label = {
                    Text(stringResource(Res.string.age))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
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
                label = {
                    Text(stringResource(Res.string.email))
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
                text = stringResource(Res.string.submit)
            ) {
                events(EditProfileEvent.UpdateProfile(imageBitmap))
            }

        }
    }
}

