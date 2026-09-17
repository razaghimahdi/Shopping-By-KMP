package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image

actual fun ImageBitmap.toBytes(): ByteArray {
    val skiaBitmap = this.asSkiaBitmap()
    val skiaImage = Image.makeFromBitmap(skiaBitmap)

    // Encodes the raw Skia pixels into a standard PNG byte array
    val data = skiaImage.encodeToData(EncodedImageFormat.PNG)

    return data?.bytes ?: ByteArray(0)
}