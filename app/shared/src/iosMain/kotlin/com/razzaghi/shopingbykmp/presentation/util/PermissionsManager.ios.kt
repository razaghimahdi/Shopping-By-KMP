package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import platform.AVFoundation.*
import platform.Foundation.NSURL
import platform.Photos.*
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

actual class PermissionsManager actual constructor(
    private val callback: PermissionCallback
) : PermissionHandler {

    @Composable
   actual override fun AskPermission(permission: PermissionType) {
        LaunchedEffect(permission) {
            when (permission) {
                PermissionType.CAMERA -> {
                    val status = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)
                    when (status) {
                        AVAuthorizationStatusAuthorized -> callback.onPermissionStatus(permission, PermissionStatus.GRANTED)
                        AVAuthorizationStatusNotDetermined -> {
                            AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) { granted ->
                                val newStatus = if (granted) PermissionStatus.GRANTED else PermissionStatus.DENIED
                                callback.onPermissionStatus(permission, newStatus)
                            }
                        }
                        else -> callback.onPermissionStatus(permission, PermissionStatus.DENIED)
                    }
                }
                PermissionType.GALLERY -> {
                    val status = PHPhotoLibrary.authorizationStatus()
                    when (status) {
                        PHAuthorizationStatusAuthorized -> callback.onPermissionStatus(permission, PermissionStatus.GRANTED)
                        PHAuthorizationStatusNotDetermined -> {
                            PHPhotoLibrary.requestAuthorization { newStatus ->
                                val mappedStatus = if (newStatus == PHAuthorizationStatusAuthorized) PermissionStatus.GRANTED else PermissionStatus.DENIED
                                callback.onPermissionStatus(permission, mappedStatus)
                            }
                        }
                        else -> callback.onPermissionStatus(permission, PermissionStatus.DENIED)
                    }
                }
            }
        }
    }

    @Composable
    actual override fun isPermissionGranted(permission: PermissionType): Boolean {
        return when (permission) {
            PermissionType.CAMERA -> {
                AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo) == AVAuthorizationStatusAuthorized
            }
            PermissionType.GALLERY -> {
                PHPhotoLibrary.authorizationStatus() == PHAuthorizationStatusAuthorized
            }
        }
    }

    @Composable
    actual override fun LaunchSettings() {
        LaunchedEffect(Unit) {
            val url = NSURL(string = UIApplicationOpenSettingsURLString)
            if (UIApplication.sharedApplication.canOpenURL(url)) {
                UIApplication.sharedApplication.openURL(url)
            }
        }
    }
}

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return remember { PermissionsManager(callback) }
}