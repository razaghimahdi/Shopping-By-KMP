package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun ChangeStatusBarColors(statusBarColor: Color) {
    // No-Op. Desktop Window architectures (Windows/macOS/Linux) do not have mobile status bars.
}