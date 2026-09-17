package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.files.FileReader

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager {
    return remember {
        CameraManager(
            onLaunch = {
                val input = document.createElement("input") as HTMLInputElement
                input.type = "file"
                input.accept = "image/*"
                input.setAttribute("capture", "environment")

                input.onchange = { event: Event ->
                    val file = input.files?.item(0)

                    if (file != null) {
                        val reader = FileReader()
                        reader.onloadend = {
                            val arrayBuffer = reader.result as? org.khronos.webgl.ArrayBuffer
                            if (arrayBuffer != null) {
                                val dataView = org.khronos.webgl.DataView(arrayBuffer)

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