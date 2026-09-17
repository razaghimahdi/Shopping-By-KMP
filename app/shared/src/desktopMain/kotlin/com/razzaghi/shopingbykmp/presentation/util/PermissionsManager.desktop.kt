package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

actual class PermissionsManager actual constructor(
    private val callback: PermissionCallback
) : PermissionHandler {

    @Composable
    actual override fun AskPermission(permission: PermissionType) {
        // Desktop and Web OS layers intercept hardware requests natively
        LaunchedEffect(permission) {
            callback.onPermissionStatus(permission, PermissionStatus.GRANTED)
        }
    }

    @Composable
    actual override fun isPermissionGranted(permission: PermissionType): Boolean {
        return true
    }

    @Composable
    actual override fun LaunchSettings() {
        // No-Op: Settings redirection is typically a mobile-only concept
    }
}

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return remember { PermissionsManager(callback) }
}