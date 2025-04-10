package common

import androidx.compose.runtime.Composable

actual class PermissionsManager actual constructor(private val callback: PermissionCallback) : PermissionHandler {
    @Composable
    override fun AskPermission(permission: PermissionType) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean {
        TODO("Not yet implemented")
    }

    @Composable
    override fun LaunchSettings() {
        TODO("Not yet implemented")
    }

}

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return PermissionsManager(callback)
}
