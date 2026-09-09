package presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.constants.CUSTOM_TAG
import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewSingleAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.no_wifi


@Composable
fun DefaultScreenUI(
    errors: Flow<UIComponent> = MutableSharedFlow(),
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    networkState: NetworkState = NetworkState.Good,
    onTryAgain: () -> Unit = {},
    titleToolbar: String? = null,
    startIconToolbar: ImageVector? = null,
    endIconToolbar: ImageVector? = null,
    onClickStartIconToolbar: () -> Unit = {},
    onClickEndIconToolbar: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val errorQueue = remember {
        mutableStateOf<Queue<UIComponent>>(Queue(mutableListOf()))
    }


    Scaffold(
        topBar = {
            if (titleToolbar != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (startIconToolbar != null) {
                        CircleButton(
                            imageVector = startIconToolbar,
                            onClick = { onClickStartIconToolbar() })
                    } else {
                        Spacer_16dp()
                    }
                    Text(titleToolbar, style = MaterialTheme.typography.titleLarge)

                    if (endIconToolbar != null) {
                        CircleButton(
                            imageVector = endIconToolbar,
                            onClick = { onClickEndIconToolbar() })
                    } else {
                        Spacer_16dp()
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            content()


            LaunchedEffect(errors) {
                errors.collect { errors ->
                    errorQueue.appendToMessageQueue(errors)
                }
            }


            // process the queue
            if (!errorQueue.value.isEmpty()) {
                errorQueue.value.peek()?.let { uiComponent ->
                    if (uiComponent is UIComponent.Dialog) {
                        CreateUIComponentDialog(
                            title = uiComponent.alert.title,
                            description = uiComponent.alert.message,
                            onRemoveHeadFromQueue = { errorQueue.removeHeadMessage() }
                        )
                    }
                    if (uiComponent is UIComponent.ToastSimple) {
                        ShowSnackbar(
                            title = uiComponent.title,
                            snackbarVisibleState = true,
                            onDismiss = { errorQueue.removeHeadMessage() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }

            if (networkState == NetworkState.Failed && progressBarState == ProgressBarState.Idle) {
                FailedNetworkScreen(onTryAgain = onTryAgain)
            }

            if (progressBarState is ProgressBarState.LoadingWithLogo) {
                LoadingWithLogoScreen()

            }


            if (progressBarState is ProgressBarState.ScreenLoading || progressBarState is ProgressBarState.FullScreenLoading) {
                CircularProgressIndicator()
            }


        }
    }
}


private fun MutableState<Queue<UIComponent>>.appendToMessageQueue(uiComponent: UIComponent) {
    if (uiComponent is UIComponent.None) {
        println("$CUSTOM_TAG appendToMessageQueue:  ${uiComponent.message}")
        return
    }

    val queue = this.value
    queue.add(uiComponent)

    this.value = Queue(mutableListOf()) // force to recompose
    this.value = queue
}

private fun MutableState<Queue<UIComponent>>.removeHeadMessage() {
    if (this.value.isEmpty()) {
        println("$CUSTOM_TAG: removeHeadMessage: Nothing to remove from DialogQueue")
        return
    }
    val queue = this.value
    queue.remove() // can throw exception if empty
    this.value = Queue(mutableListOf()) // force to recompose
    this.value = queue
}


@Composable
fun <Effect : ViewSingleAction> EffectHandler(
    effectFlow: Flow<Effect>,
    onHandleEffect: (Effect) -> Unit
) {
    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            onHandleEffect(effect)
        }
    }
}

@Composable

fun FailedNetworkScreen(onTryAgain: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painterResource(Res.drawable.no_wifi), null)
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = "You are currently offline, please reconnect and try again.",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))

        DefaultButton(
            text = "Try Again",
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    DEFAULT__BUTTON_SIZE
                )
        ) {
            onTryAgain()
        }


    }

}

@Composable
fun LoadingWithLogoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
/*

            Image(
                painterResource("logo.xml"),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer_16dp()
*/

            CircularProgressIndicator()

        }


    }
}













