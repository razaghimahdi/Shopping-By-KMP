package presentation.component


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun MultiStateAnimationCircleFilledCanvas(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    radiusEnd: Float = 900f
) {
    val transition = rememberInfiniteTransition()
    val floatAnim by transition.animateFloat(
        initialValue = 700f,
        targetValue = radiusEnd,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Reverse)
    )

    Canvas(modifier = modifier) {
        val centerOffset = Offset(size.width / 2, size.height / 2)

        drawCircle(
            color = color.copy(alpha = .2f),
            radius = floatAnim,
            center = centerOffset,
        )
        drawCircle(
            color = color,
            radius = floatAnim / 4f,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = .4f),
            radius = floatAnim / 1.6f,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = .3f),
            radius = floatAnim / 1.15f,
            center = centerOffset,
        )
    }
}
