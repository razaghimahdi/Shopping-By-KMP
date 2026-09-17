package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable

@Composable
expect fun MapComponent(
    onLatitude: (Double) -> Unit,
    onLongitude: (Double) -> Unit,
)