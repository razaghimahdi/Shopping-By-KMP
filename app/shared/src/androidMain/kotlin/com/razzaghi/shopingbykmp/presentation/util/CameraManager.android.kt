package com.razzaghi.shopingbykmp.presentation.util

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager {
    val context = LocalContext.current
    var tempPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success && tempPhotoUri != null) {
                val bytes = context.contentResolver.openInputStream(tempPhotoUri!!)?.use { it.readBytes() }
                onResult(SharedImage(bytes))
            } else {
                onResult(null)
            }
        }
    )

    return remember {
        CameraManager(
            onLaunch = {
                val tempFile = File.createTempFile("camera_image", ".jpg", context.cacheDir)

                val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", tempFile)
                tempPhotoUri = uri
                cameraLauncher.launch(uri)
            }
        )
    }
}