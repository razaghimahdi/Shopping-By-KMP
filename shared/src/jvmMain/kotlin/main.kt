import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import common.Context
import presentation.App

fun main() {
    singleWindowApplication(
        title = "Shopping App",
        alwaysOnTop = true,
        state = WindowState(width = 512.dp, height = 1024.dp)
    ) {
        App(Context)
    }
}
