package presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import coil3.network.CacheStrategy

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberCustomImagePainter(
    model: Any?,
    imageLoader: ImageLoader,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model, imageLoader,
    error = painterResource("default_image_loader.png"),
    placeholder = painterResource("default_image_loader.png"),
    contentScale = contentScale
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun rememberCustomImagePainter(
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model,
    error = painterResource("default_image_loader.png"),
    placeholder = painterResource("default_image_loader.png"),
    contentScale = contentScale,
)