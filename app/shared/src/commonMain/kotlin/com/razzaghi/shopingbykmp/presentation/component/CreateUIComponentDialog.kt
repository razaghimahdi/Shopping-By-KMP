package presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.theme.DefaultCardColorsTheme


@Composable
fun CreateUIComponentDialog(
    title: String,
    description: String,
    onRemoveHeadFromQueue: () -> Unit
) {

    GenericDialog(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        title = title,
        description = description,
        onRemoveHeadFromQueue = onRemoveHeadFromQueue
    )

}


@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onRemoveHeadFromQueue: () -> Unit,
) {
    CustomAlertDialog(
        onDismissRequest = {
            onRemoveHeadFromQueue()
        },
        modifier = modifier
    ) {

        Card(
            colors = DefaultCardColorsTheme(), modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = title
                )
                Text(text = description)
            }
        }

    }
}




