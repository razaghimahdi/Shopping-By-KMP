package com.razzaghi.shopingbykmp.presentation.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayOutputStream

actual fun ImageBitmap.toBytes(): ByteArray {
    val bitmap = this.asAndroidBitmap()
    val stream = ByteArrayOutputStream()
    // Using PNG format to safely preserve any transparency (alpha channels)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}