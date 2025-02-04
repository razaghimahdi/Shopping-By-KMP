package common

import androidx.compose.ui.graphics.ImageBitmap
import org.jetbrains.skia.Bitmap

actual class SharedImage(private val bitmap: Bitmap?) {
    actual fun toByteArray(): ByteArray? {
       // val compressedBitmap = compressImage(bitmap ?: return null)
        return null
    }

    actual fun toImageBitmap(): ImageBitmap? {
        val byteArray = toByteArray()
        return if (byteArray != null) {
            return null
        } else {
            null
        }
    }
}


