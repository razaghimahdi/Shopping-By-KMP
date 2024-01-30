package presentation.ui.main.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import presentation.component.AddCommentDialog
import presentation.component.CircleButton
import presentation.component.DefaultScreenUI
import presentation.theme.BorderColor
import presentation.ui.main.comment.view_model.CommentEvent
import presentation.ui.main.comment.view_model.CommentState
import presentation.ui.main.detail.CommentBox


@Composable
fun CommentScreen(state: CommentState, events: (CommentEvent) -> Unit, popup: () -> Unit) {

    if (state.addCommentDialogState == UIComponentState.Show) {
        AddCommentDialog(onDismissRequest = {
            events(CommentEvent.OnUpdateAddCommentDialogState(UIComponentState.Hide))
        },
            onExecute = { rate, comment ->
                events(CommentEvent.AddComment(rate = rate, comment = comment))
            })
    }

    DefaultScreenUI(queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(CommentEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CommentEvent.OnRetryNetwork) }) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircleButton(imageVector = Icons.Filled.ArrowBack, onClick = { popup() })
                Text("Comments", style = MaterialTheme.typography.titleLarge)
                CircleButton(
                    imageVector = Icons.Filled.AddComment,
                    onClick = { events(CommentEvent.OnUpdateAddCommentDialogState(UIComponentState.Show)) })
            }


            if (state.comments.isEmpty()) {
                Text(
                    "No Comments!",
                    style = MaterialTheme.typography.titleLarge,
                    color = BorderColor,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }


            LazyColumn {
                items(state.comments,key = {it.id}){
                    CommentBox(comment = it, modifier = Modifier.fillMaxWidth())
                }
            }

        }
    }
}

