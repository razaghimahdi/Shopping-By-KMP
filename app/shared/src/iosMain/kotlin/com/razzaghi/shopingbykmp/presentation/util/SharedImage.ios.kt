package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual class SharedImage actual constructor(private val byteArray: ByteArray?) {

    actual fun toByteArray(): ByteArray? = byteArray

    actual fun toImageBitmap(): ImageBitmap? {
        return byteArray?.let { bytes ->
            // Skia handles the decoding for Apple, JVM, and Web canvases
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        }
    }
}