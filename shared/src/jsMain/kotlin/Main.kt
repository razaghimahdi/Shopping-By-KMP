
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import presentation.App

@Composable
fun MainView() {
    Box(Modifier.background(color = Color(0xFF1A1E1F)).fillMaxSize()) {
//        CompositionLocalProvider(
//            LocalImageLoader provides ImageLoader {
//                components {
//                    setupDefaultComponents()
//                }
//                interceptor {
//                    bitmapMemoryCacheConfig(
//                        valueHashProvider = { identityHashCode(it) },
//                        valueSizeProvider = { 500 },
//                        block = fun MemoryCacheBuilder<MemoryKey, Bitmap>.() {
//                            maxSizePercent(0.25)
//                        }
//                    )
//                }
//            },
//        ) {
        App(context = null)
        // }
    }
}