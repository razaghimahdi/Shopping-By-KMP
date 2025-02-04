package common

 import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager {
    return remember {
        GalleryManager(onLaunch = {
        })
    }
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}