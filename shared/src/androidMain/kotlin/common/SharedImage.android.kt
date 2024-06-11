package common

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

actual class SharedImage(private val bitmap: android.graphics.Bitmap?) {
    actual fun toByteArray(): ByteArray? {
       // val compressedBitmap = compressImage(bitmap ?: return null)
        return if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(
                android.graphics.Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream
            )
            byteArrayOutputStream.toByteArray()
        } else {
            null
        }
    }

    actual fun toImageBitmap(): ImageBitmap? {
        val byteArray = toByteArray()
        return if (byteArray != null) {
            return compressImage(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))?.asImageBitmap()
        } else {
            null
        }
    }
}


private fun compressImage(bmp: android.graphics.Bitmap): android.graphics.Bitmap? {
    val baos = ByteArrayOutputStream()
    bmp.compress(
        android.graphics.Bitmap.CompressFormat.PNG,
        100,
        baos
    )
    var options = 90
    while (baos.toByteArray().size / 1024 > 400) {
        baos.reset()
        bmp.compress(
            android.graphics.Bitmap.CompressFormat.PNG,
            options,
            baos
        )
        options -= 10
    }
    val isBm =
        ByteArrayInputStream(baos.toByteArray())

    return BitmapFactory.decodeStream(isBm, null, null)
}