package presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.theme.DefaultTextFieldTheme
import presentation.theme.IconColorGrey

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String = "Password",
    readOnly: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = enabled) {
        if (!enabled) isPasswordVisible.value = false
    }

    TextField(
        isError = isError,
        modifier = modifier,
        colors = DefaultTextFieldTheme(),
        shape = MaterialTheme.shapes.small,
        readOnly = readOnly,
        value = value,
        label = {
            Text(text = label)
        },
        onValueChange = { onValueChange(it) },
        trailingIcon = {
            IconButton(onClick = {
                if (enabled) {
                    isPasswordVisible.value = !isPasswordVisible.value
                }
            }) {
                when (isPasswordVisible.value) {
                    true -> Icon(
                        painter = painterResource("ic_password_hide.xml"),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )

                    false -> Icon(
                        painter = painterResource("ic_password_show.xml"),
                        contentDescription = null,
                        tint = IconColorGrey,
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
        ),
        visualTransformation = when (isPasswordVisible.value) {
            true -> VisualTransformation.None
            false -> PasswordVisualTransformation()
        },
    )
}
