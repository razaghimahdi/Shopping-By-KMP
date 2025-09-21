import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport // <-- Import changed
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        // Targets an HTML element with the id "composeApp"
        ComposeViewport(viewportContainerId = "ComposeTarget") {
            MainView()
        }
    }
}