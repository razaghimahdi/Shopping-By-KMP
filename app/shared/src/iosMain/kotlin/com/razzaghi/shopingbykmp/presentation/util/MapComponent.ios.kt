package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import globalLatitude
import globalLongitude
import mapViewController

@Composable
actual fun MapComponent(
    onLatitude: (Double) -> Unit,
    onLongitude: (Double) -> Unit,
) {
    onLatitude(globalLatitude)
    onLongitude(globalLongitude)
    UIKitViewController(
        factory = mapViewController,
        modifier = Modifier.fillMaxSize(),
    )
}