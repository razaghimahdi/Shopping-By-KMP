import androidx.compose.ui.window.ComposeUIViewController
import common.Context
import presentation.App

fun mainViewController() = ComposeUIViewController { App(Context()) }

