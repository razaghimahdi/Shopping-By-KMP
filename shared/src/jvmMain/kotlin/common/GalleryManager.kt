package common

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGalleryManager(onResult: (SharedImage?) -> Unit): GalleryManager = GalleryManager{}


actual class GalleryManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {}
}
