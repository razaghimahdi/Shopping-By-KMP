package common

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.reinterpret
import org.jetbrains.skia.Image
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation

actual class SharedImage(private val image: UIImage?) {
    @OptIn(ExperimentalForeignApi::class)
    actual fun toByteArray(): ByteArray? {
        val compressedImage = compressImage(image ?: return null)
        return if (compressedImage != null) {
            val imageData = UIImageJPEGRepresentation(compressedImage, COMPRESSION_QUALITY)
                ?: throw IllegalArgumentException("image data is null")
            val bytes = imageData.bytes ?: throw IllegalArgumentException("image bytes is null")
            val length = imageData.length

            val data: CPointer<ByteVar> = bytes.reinterpret()
            ByteArray(length.toInt()) { index -> data[index] }
        } else {
            null
        }

    }


    actual fun toImageBitmap(): ImageBitmap? {
        val byteArray = toByteArray()
        return if (byteArray != null) {
            Image.makeFromEncoded(byteArray).toComposeImageBitmap()
        } else {
            null
        }
    }

    private companion object {
        const val COMPRESSION_QUALITY = 0.99
    }

}


private fun compressImage(uiImage: UIImage): UIImage? {
    var photo = uiImage
    var resizedImageData = UIImageJPEGRepresentation(photo, 1.0)
    var imageSize: Int = resizedImageData?.length?.toInt() ?: -1

    if (imageSize == -1) return null
    if (resizedImageData == null) return null

    var tryCounter: Int = 5
    while (imageSize > 500000) {
        resizedImageData = UIImageJPEGRepresentation(photo, 0.75)
        imageSize = resizedImageData?.length?.toInt() ?: -1
        if (resizedImageData == null) return null
        photo = UIImage(data = resizedImageData, scale = 0.0)
        tryCounter -= 1
        if (tryCounter == 0) {
            return null
        }
    }
    if (imageSize < 500000) {
        return photo
    } else {
        return null
    }
}
