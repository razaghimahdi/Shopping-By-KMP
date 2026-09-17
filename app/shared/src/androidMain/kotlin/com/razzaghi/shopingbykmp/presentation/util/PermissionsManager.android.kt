@file:OptIn(ExperimentalPermissionsApi::class) // FIX: Moved OptIn to the file level to prevent signature mismatches!
package com.razzaghi.shopingbykmp.presentation.util

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.*

actual class PermissionsManager actual constructor(
    private val callback: PermissionCallback
) : PermissionHandler {

    @Composable
    actual override fun AskPermission(permission: PermissionType) {
        val androidPermission = permission.toAndroidPermission()
        val permissionState = rememberPermissionState(androidPermission)

        // Trigger the callback whenever the permission status changes
        LaunchedEffect(permissionState.status) {
            val status = when {
                permissionState.status.isGranted -> PermissionStatus.GRANTED
                permissionState.status.shouldShowRationale -> PermissionStatus.SHOW_RATIONAL
                else -> PermissionStatus.DENIED
            }
            callback.onPermissionStatus(permission, status)
        }

        LaunchedEffect(Unit) {
            if (!permissionState.status.isGranted) {
                permissionState.launchPermissionRequest()
            }
        }
    }

    @Composable
    actual override fun isPermissionGranted(permission: PermissionType): Boolean {
        val androidPermission = permission.toAndroidPermission()
        val permissionState = rememberPermissionState(androidPermission)
        return permissionState.status.isGranted
    }

    @Composable
    actual override fun LaunchSettings() {
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent)
        }
    }

    private fun PermissionType.toAndroidPermission(): String {
        return when (this) {
            PermissionType.CAMERA -> Manifest.permission.CAMERA
            PermissionType.GALLERY -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }
            }
        }
    }
}

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return remember { PermissionsManager(callback) }
}