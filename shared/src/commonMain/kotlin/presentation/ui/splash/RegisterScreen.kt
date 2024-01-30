package presentation.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import presentation.component.DEFAULT__BUTTON_SIZE_EXTRA
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.PasswordTextField
import presentation.component.SimpleImageButton
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.theme.DefaultCheckBoxTheme
import presentation.theme.DefaultTextFieldTheme
import presentation.ui.splash.view_model.LoginEvent
import presentation.ui.splash.view_model.LoginState

@Composable
fun RegisterScreen(
    state: LoginState,
    events: (LoginEvent) -> Unit,
    navigateToMain: () -> Unit,
    popUp: () -> Unit
) {

    LaunchedEffect(state.navigateToMain) {
        if (state.navigateToMain) {
            navigateToMain()
        }
    }

    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(LoginEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Create Account", style = MaterialTheme.typography.displaySmall)
            Spacer_16dp()
            Text(
                "Fill your information below or register with your social account.",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer_32dp()

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text("Name")
                Spacer_4dp()
                TextField(
                    value = state.nameRegister,
                    onValueChange = {
                        events(LoginEvent.OnUpdateNameRegister(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                    ),
                )
                Spacer_8dp()


                Text("Email")
                Spacer_4dp()
                TextField(
                    isError = isUsernameError,
                    value = state.usernameLogin,
                    onValueChange = {
                        if (it.length < 32) {
                            events(LoginEvent.OnUpdateUsernameLogin(it))
                            isUsernameError = it.isEmpty()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                    ),
                )
                AnimatedVisibility(visible = isUsernameError) {
                    Text(
                        "Enter valid email",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer_8dp()

                Text("Password")
                Spacer_4dp()
                PasswordTextField(
                   // isError = isPasswordError,
                    value = state.passwordLogin,
                    onValueChange = {
                        events(LoginEvent.OnUpdatePasswordLogin(it))
                        isPasswordError = it.length < 8
                    },
                    modifier = Modifier.fillMaxWidth(),
                )

                AnimatedVisibility(visible = isPasswordError) {
                    Text(
                        "Enter valid password",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer_8dp()


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Checkbox(
                    checked = isChecked, onCheckedChange = { isChecked = it },
                    colors = DefaultCheckBoxTheme()
                )
                Text(
                    text = buildAnnotatedString {
                        append("Agree with ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("Terms & Condition")
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                )
            }


            Spacer_32dp()


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultButton(
                    progressBarState = state.progressBarState,
                    text = "Sign Up",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DEFAULT__BUTTON_SIZE_EXTRA),
                    onClick = { events(LoginEvent.Register) }
                )

                Spacer(Modifier.height(32.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Divider(modifier = Modifier.width(75.dp))
                    Text(text = "Or sign up with")
                    Divider(modifier = Modifier.width(75.dp))
                }
                Spacer_32dp()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SimpleImageButton("facebook.xml")
                    SimpleImageButton("apple.xml")
                    SimpleImageButton("google.xml")
                }

            }

            Spacer_32dp()



            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Already have an account?",
                )
                Spacer_4dp()
                Text(
                    modifier = Modifier.clickable {
                        popUp()
                    },
                    text = "Sign In",
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            }

        }
    }
}