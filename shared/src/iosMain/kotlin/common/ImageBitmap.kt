package common


import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import org.jetbrains.skia.Image


actual fun ImageBitmap.toBytes(): ByteArray {
    val skiaBitmap = this.asSkiaBitmap()
    val image = Image.makeFromBitmap(skiaBitmap)

    val imageSize = image.imageInfo.minRowBytes * image.height
   // val byteArray = ByteArray(imageSize.toInt())


    val bytes = image.encodeToData()?.bytes


    return bytes!!
}


