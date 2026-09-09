package presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import business.core.ProgressBarState
import presentation.theme.BorderColor
import presentation.theme.DefaultButtonTheme
import presentation.theme.DefaultButtonWithBorderPrimaryTheme
import presentation.theme.DefaultCardColorsTheme

val DEFAULT__BUTTON_SIZE = 50.dp
val DEFAULT__BUTTON_SIZE_EXTRA = 60.dp


@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.size(50.dp),
        shape = CircleShape,
        colors = DefaultCardColorsTheme(),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, BorderColor),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector, null)
        }
    }
}

@Composable
fun ButtonLoading(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = (enabled || progressBarState != ProgressBarState.Idle),
        modifier = modifier,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AnimatedVisibility(visible = (progressBarState == ProgressBarState.ButtonLoading || progressBarState == ProgressBarState.FullScreenLoading)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(25.dp),
                    strokeWidth = 2.dp,
                    color = if (enabled) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                )
            }

            content()
        }
    }
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    enabled: Boolean = true,
    enableElevation: Boolean = false,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    text: String,
    onClick: () -> Unit,
) {
    ButtonLoading(
        enabled = enabled,
        modifier = modifier,
        elevation = if (enableElevation) ButtonDefaults.buttonElevation() else ButtonDefaults.buttonElevation(
            0.dp
        ),
        colors = if (enabled) DefaultButtonTheme() else DefaultButtonWithBorderPrimaryTheme(),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        shape = shape,
        onClick = onClick,
        progressBarState = progressBarState,
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}


@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = DefaultButtonTheme(),
        shape = shape,
        onClick = onClick
    ) {
        Icon(icon, null, tint = MaterialTheme.colorScheme.background)
        Spacer_4dp()
        Text(
            text = text,
            style = style,
        )
    }
}