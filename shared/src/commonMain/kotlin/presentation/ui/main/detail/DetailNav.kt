package presentation.ui.main.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import org.koin.compose.koinInject
import presentation.navigation.DetailNavigation
import presentation.ui.main.comment.CommentScreen
import presentation.ui.main.comment.view_model.CommentEvent
import presentation.ui.main.comment.view_model.CommentViewModel
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel

@Composable
fun DetailNav(id: Long, popUp: () -> Unit) {
    val backStack = rememberNavBackStack(
        SavedStateConfiguration.DEFAULT,
        DetailNavigation.Detail
    )

    NavDisplay(
        backStack = backStack,
        modifier = Modifier.fillMaxSize(),
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            } else {
                popUp()
            }
        },
        entryProvider = { key ->
            when (val destination = key as DetailNavigation) {
                DetailNavigation.Detail -> NavEntry(destination) {
                    val viewModel: DetailViewModel = koinInject()
                    LaunchedEffect(id) {
                        viewModel.onTriggerEvent(DetailEvent.GetProduct(id))
                    }
                    DetailScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popup = {
                            popUp()
                        },
                        navigateToMoreComment = {
                            backStack.add(DetailNavigation.Comment(it))
                        }
                    )
                }

                is DetailNavigation.Comment -> NavEntry(destination) {
                    val viewModel: CommentViewModel = koinInject()
                    val commentId = destination.id

                    LaunchedEffect(commentId) {
                        viewModel.onTriggerEvent(CommentEvent.OnUpdateProductId(commentId))
                        viewModel.onTriggerEvent(CommentEvent.GetComments)
                    }

                    CommentScreen(
                        state = viewModel.state.value,
                        events = viewModel::onTriggerEvent,
                        errors = viewModel.errors,
                        popup = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }
            }
        }
    )
}