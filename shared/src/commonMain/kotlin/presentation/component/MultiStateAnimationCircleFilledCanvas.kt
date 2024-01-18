package presentation.component


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

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
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )
    val floatAnim2 by transition.animateFloat(
        initialValue = 100f,
        targetValue = radiusEnd * 2.2f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )
    Canvas(modifier = modifier) {
        val centerOffset = Offset(size.width / 2, size.height / 2)

        // Draw the circle with the border (borderColor and borderWidth)
        /*drawCircle(
            color = color.copy(alpha = .3f),
            radius = floatAnim2 , // Slightly larger radius to create the border effect
            center = centerOffset,
            style =  Stroke(width = 5f)
        )
        drawCircle(
            color = color.copy(alpha = .3f),
            radius = floatAnim2 / 1.2f, // Slightly larger radius to create the border effect
            center = centerOffset,
            style =  Stroke(width = 5f)
        )
        drawCircle(
            color = color.copy(alpha = .3f),
            radius = floatAnim2 / 1.5f, // Slightly larger radius to create the border effect
            center = centerOffset,
            style =  Stroke(width = 5f)
        )*/


        drawCircle(
            color = color,
            radius = floatAnim / 4f,
            center = centerOffset,
        )
        drawCircle(
            color = color.copy(alpha = .2f),
            radius = floatAnim,
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