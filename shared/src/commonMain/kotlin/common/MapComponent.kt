package common

import androidx.compose.runtime.Composable

@Composable
expect fun MapComponent(
    context: Context,
    onLatitude: (Double) -> Unit,
    onLongitude: (Double) -> Unit,
)