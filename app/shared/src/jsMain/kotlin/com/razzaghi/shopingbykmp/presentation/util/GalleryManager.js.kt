package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.files.FileReader
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.DataView

actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    return remember {
        GalleryManager(
            onLaunch = {
                val input = document.createElement("input") as HTMLInputElement
                input.type = "file"
                input.accept = "image/*"
                // No 'capture' attribute here, so it defaults to the gallery!

                input.onchange = { event: Event ->
                    val file = input.files?.item(0)

                    if (file != null) {
                        val reader = FileReader()
                        reader.onloadend = {
                            val arrayBuffer = reader.result as? ArrayBuffer
                            if (arrayBuffer != null) {
                                // Safe DataView extraction to prevent Wasm array crashes
                                val dataView = DataView(arrayBuffer)
                                val byteArray = ByteArray(arrayBuffer.byteLength) { i ->
                                    dataView.getInt8(i)
                                }
                                onResult(SharedImage(byteArray))
                            } else {
                                onResult(null)
                            }
                        }
                        reader.readAsArrayBuffer(file)
                    } else {
                        onResult(null)
                    }
                    null
                }
                input.click()
            }
        )
    }
}