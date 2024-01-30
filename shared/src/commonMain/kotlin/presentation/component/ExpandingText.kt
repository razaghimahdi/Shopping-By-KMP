package presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.TextLayoutResult


private const val MINIMIZED_MAX_LINES = 2

@Composable
fun ExpandingText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onExpandedChange: (Boolean) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    val finalText= mutableStateOf(text)

    val endOfTitle =
        if (isExpanded) "Show Less" else "Read More"

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect


        when {
            isExpanded -> {
                finalText.value = "$text $endOfTitle"
            }

            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "... $endOfTitle"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText.value = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

    Text(
        text = finalText.value,
        maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
        style = style,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = modifier
            .clickable(enabled = isClickable) {
                isExpanded = !isExpanded
                onExpandedChange(isExpanded)
            }
            .animateContentSize(),
    )
}