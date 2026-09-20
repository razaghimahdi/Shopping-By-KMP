package com.razzaghi.shopingbykmp.presentation.util

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual class SharedImage actual constructor(private val byteArray: ByteArray?) {

    actual fun toByteArray(): ByteArray? = byteArray

    actual fun toImageBitmap(): ImageBitmap? {
        return byteArray?.let { bytes ->
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
        }
    }
}