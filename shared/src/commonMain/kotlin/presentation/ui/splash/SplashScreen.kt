package presentation.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.img_headphone_splash
import shoping_by_kmp.shared.generated.resources.img_shoe1_splash
import shoping_by_kmp.shared.generated.resources.img_shoe2_splash
import shoping_by_kmp.shared.generated.resources.img_watch_splash

fun getSplashList() = listOf(
    Res.drawable.img_headphone_splash,
    Res.drawable.img_shoe2_splash,
    Res.drawable.img_watch_splash,
    Res.drawable.img_shoe1_splash,
)

@Composable
internal fun SplashScreen() {

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { getSplashList().size })
    val showProducts = produceState(initialValue = false) {
        delay(1000)
        value = true
    }

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    ) {
        Circle(modifier = Modifier.align(Alignment.TopCenter))

        AnimatedVisibility(
            showProducts.value,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 500)
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 500)
            )
        ) {

            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(end = 50.dp, start = 140.dp),
                userScrollEnabled = true,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    snapPositionalThreshold = 0.25f
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) { page ->

                val isCurrent = page == pagerState.currentPage

                val widthAnim = animateDpAsState(targetValue = if (isCurrent) 150.dp else 120.dp)
                val heightAnim = animateDpAsState(targetValue = if (isCurrent) 200.dp else 150.dp)


                val infiniteTransition = rememberInfiniteTransition(label = "floating")

                val offsetY by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = if (isCurrent) 20f else 0f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 2000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ), label = "offsetY"
                )

                Image(
                    painter = painterResource(getSplashList()[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.width(widthAnim.value).height(heightAnim.value)
                        .offset(y = offsetY.dp)
                )
            }
        }

        CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp))


    }

}

@Composable
private fun Circle(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    radiusEnd: Float = 1800f
) {

    val targetValue = produceState(initialValue = 0f) {
        delay(500)
        value = radiusEnd
    }

    val floatAnim = animateFloatAsState(
        animationSpec = keyframes {
            durationMillis = 200
        },
        targetValue = targetValue.value
    )

    Canvas(modifier = modifier) {
        val centerOffset = Offset(size.width / 2, size.height / 2)

        drawCircle(
            color = color,
            radius = floatAnim.value,
            center = centerOffset
        )
    }
}


