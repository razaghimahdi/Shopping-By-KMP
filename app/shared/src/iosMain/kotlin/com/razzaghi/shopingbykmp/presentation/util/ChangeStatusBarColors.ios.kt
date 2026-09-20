package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun ChangeStatusBarColors(statusBarColor: Color) {
    // No-Op.
    // On iOS, the status bar automatically becomes the color of the Compose background
    // that touches the top edge of the screen (the Safe Area).
    // If you need to change the status bar content (black vs white text),
    // it must be done natively in Xcode via `preferredStatusBarStyle`.
}