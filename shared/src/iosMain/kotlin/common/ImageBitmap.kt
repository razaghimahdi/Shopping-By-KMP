package common


import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.toPixelMap
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CValuesRef
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.get
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.usePinned
import platform.UIKit.UIImage
import platform.Foundation.NSData
import platform.Foundation.create
import platform.Foundation.*
import platform.UIKit.UIImageJPEGRepresentation
import platform.posix.memcpy

import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.posix.size_t


@OptIn(ExperimentalForeignApi::class)
actual fun ImageBitmap.toBytes(): ByteArray {
    val skiaBitmap = this.asSkiaBitmap()
    val image = Image.makeFromBitmap(skiaBitmap)

    val imageSize = image.imageInfo.minRowBytes * image.height
   // val byteArray = ByteArray(imageSize.toInt())


    val bytes = image.encodeToData()?.bytes


    return bytes!!
}


