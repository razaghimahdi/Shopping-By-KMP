package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val darkColorPalette = darkColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    secondary = AccentColor,
)

private val lightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    primaryContainer = PrimaryVariantColor,
    secondary = AccentColor,
    background = Color.White,
    surfaceVariant = Color.White,
    surface = lightSurface,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colorScheme = lightColorPalette,
        typography = LatoTypography(),
        shapes = Shapes,
        content = content
    )
}