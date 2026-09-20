package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImage(byteArray: ByteArray?) {
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
}