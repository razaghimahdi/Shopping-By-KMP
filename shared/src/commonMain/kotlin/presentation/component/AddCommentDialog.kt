package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import presentation.theme.BorderColor
import presentation.theme.DefaultTextFieldTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCommentDialog(onDismissRequest: () -> Unit, onExecute: (Double, String) -> Unit) {

    var comment by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf(5.0) }


    CustomAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background).padding(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Spacer_16dp()

            Text(
                "Add",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer_32dp()

            Text(
                "Rate:",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )

            Slider(
                value = rate.toFloat(),
                onValueChange = { rate = it.toDouble() },
                valueRange = 0f..5f,
                steps = 4,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "0",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "5",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer_16dp()


            Text(
                "Comment:",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium
            )

            TextField(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .border(1.dp, BorderColor, MaterialTheme.shapes.small),
                value = comment, onValueChange = { comment = it },
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Default,
                    keyboardType = KeyboardType.Text,
                ),
            )


            Spacer_32dp()

            DefaultButton(modifier = Modifier.fillMaxWidth(), text = "Submit") {
                onExecute(rate, comment)
                onDismissRequest()
            }

            Spacer_16dp()
        }
    }


}