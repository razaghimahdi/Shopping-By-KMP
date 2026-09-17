package com.razzaghi.shopingbykmp.presentation.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                // Read the image bytes from the selected URI
                val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                if (bytes != null) {
                    onResult(SharedImage(bytes))
                } else {
                    onResult(null)
                }
            } else {
                onResult(null)
            }
        }
    )

    return remember {
        GalleryManager(
            onLaunch = {
                // "image/*" filters the picker to only show images
                galleryLauncher.launch("image/*")
            }
        )
    }
}