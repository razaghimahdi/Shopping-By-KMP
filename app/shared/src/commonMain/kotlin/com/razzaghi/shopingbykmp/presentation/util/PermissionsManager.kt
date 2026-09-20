package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable


interface PermissionCallback {
    fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus)
}

expect class PermissionsManager(callback: PermissionCallback) : PermissionHandler {

    @Composable
    override fun AskPermission(permission: PermissionType)

    @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean

    @Composable
    override fun LaunchSettings()
}

@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionsManager