package common

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage

actual fun ImageBitmap.toBytes(): ByteArray {
    throw UnsupportedOperationException("Not supported on JVM")
}
