package common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
 import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager {
    return remember { PermissionsManager(callback) }
}

actual class PermissionsManager actual constructor(private val callback: PermissionCallback) :
    PermissionHandler {
     @Composable
    override fun AskPermission(permission: PermissionType) {
        val lifecycleOwner = LocalLifecycleOwner.current

        when (permission) {
            PermissionType.CAMERA -> {
             }

            PermissionType.GALLERY -> {
                // Granted by default because in Android GetContent API does not require any
                // runtime permissions, and i am using it to access gallery in my app
                callback.onPermissionStatus(
                    permission, PermissionStatus.GRANTED
                )
            }
        }
    }


     @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean {
        return when (permission) {
            PermissionType.CAMERA -> {
                false
             }

            PermissionType.GALLERY -> {
                // Granted by default because in Android GetContent API does not require any
                // runtime permissions, and i am using it to access gallery in my app
                true
            }
        }
    }

    @Composable
    override fun LaunchSettings() {


    }
}