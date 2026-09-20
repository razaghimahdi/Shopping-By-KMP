package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.*
import platform.darwin.NSObject
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readBytes

actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    val imagePicker = UIImagePickerController()

    val galleryDelegate = remember {
        object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
            override fun imagePickerController(
                picker: UIImagePickerController,
                didFinishPickingMediaWithInfo: Map<Any?, *>
            ) {
                val image = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage
                if (image != null) {
                    val nsData = UIImageJPEGRepresentation(image, 0.8) // 80% compression
                    val byteArray = nsData?.bytes?.readBytes(nsData.length.toInt())

                    if (byteArray != null) {
                        onResult(SharedImage(byteArray))
                    } else {
                        onResult(null)
                    }
                } else {
                    onResult(null)
                }
                picker.dismissViewControllerAnimated(true, null)
            }

            override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                onResult(null)
                picker.dismissViewControllerAnimated(true, null)
            }
        }
    }

    return remember {
        GalleryManager(
            onLaunch = {
                // Open the iOS Photo Library
                imagePicker.setSourceType(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary)
                imagePicker.setDelegate(galleryDelegate)

                UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                    imagePicker, animated = true, completion = null
                )
            }
        )
    }
}