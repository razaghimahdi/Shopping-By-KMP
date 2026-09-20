package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    return remember {
        GalleryManager(
            onLaunch = {
                // Opens the native OS file explorer window
                val dialog = FileDialog(null as Frame?, "Select an Image", FileDialog.LOAD)
                dialog.isVisible = true

                val directory = dialog.directory
                val file = dialog.file

                if (directory != null && file != null) {
                    val selectedFile = File(directory, file)
                    if (selectedFile.exists()) {
                        val bytes = selectedFile.readBytes()
                        onResult(SharedImage(bytes))
                    } else {
                        onResult(null)
                    }
                } else {
                    onResult(null) // User closed the dialog without picking
                }
            }
        )
    }
}