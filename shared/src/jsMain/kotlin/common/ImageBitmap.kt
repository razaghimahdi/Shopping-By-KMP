package common

import androidx.compose.ui.graphics.ImageBitmap

actual fun ImageBitmap.toBytes(): ByteArray {
    throw UnsupportedOperationException("Not supported on Web")
}
