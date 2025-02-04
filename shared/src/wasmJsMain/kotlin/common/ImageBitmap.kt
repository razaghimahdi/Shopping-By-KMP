package common

 import androidx.compose.ui.graphics.ImageBitmap
 import androidx.compose.ui.graphics.asSkiaBitmap

actual fun ImageBitmap.toBytes(): ByteArray {
    return ByteArray(this.asSkiaBitmap().computeByteSize())
}