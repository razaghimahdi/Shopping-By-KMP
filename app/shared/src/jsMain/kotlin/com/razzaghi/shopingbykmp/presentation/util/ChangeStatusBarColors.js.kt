package com.razzaghi.shopingbykmp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import kotlinx.browser.document
import org.w3c.dom.HTMLMetaElement

@Composable
actual fun ChangeStatusBarColors(statusBarColor: Color) {
    SideEffect {
        // Convert the Compose Color to a standard CSS rgb() string
        val r = (statusBarColor.red * 255).toInt()
        val g = (statusBarColor.green * 255).toInt()
        val b = (statusBarColor.blue * 255).toInt()
        val cssColor = "rgb($r, $g, $b)"

        // Find or create the theme-color meta tag in the HTML DOM
        var metaThemeColor = document.querySelector("meta[name=theme-color]") as? HTMLMetaElement
        if (metaThemeColor == null) {
            metaThemeColor = document.createElement("meta") as HTMLMetaElement
            metaThemeColor.name = "theme-color"
            document.head?.appendChild(metaThemeColor)
        }

        metaThemeColor.content = cssColor
    }
}