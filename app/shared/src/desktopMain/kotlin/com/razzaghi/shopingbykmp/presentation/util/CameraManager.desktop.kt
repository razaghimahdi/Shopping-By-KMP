package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager {
    return remember {
        CameraManager(
            onLaunch = {
                println("Camera capture is not natively supported on Desktop without third-party webcam libraries.")
                onResult(null)
            }
        )
    }
}