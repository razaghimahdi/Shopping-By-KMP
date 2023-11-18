import androidx.compose.ui.window.ComposeUIViewController
import common.Context
import platform.darwin.NSObject
import presentation.App

fun MainViewController() = ComposeUIViewController { App(Context()) }

