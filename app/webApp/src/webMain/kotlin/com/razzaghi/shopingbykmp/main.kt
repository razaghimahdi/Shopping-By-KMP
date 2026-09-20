package com.razzaghi.shopingbykmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.razzaghi.shopingbykmp.presentation.App
import com.razzaghi.shopingbykmp.di.appModule
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    try {
        startKoin {
            modules(appModule())
        }
    } catch (e: Exception) {
        println("Koin is already started.")
    }

    ComposeViewport(viewportContainerId = "ComposeTarget") {
        App()
    }
}