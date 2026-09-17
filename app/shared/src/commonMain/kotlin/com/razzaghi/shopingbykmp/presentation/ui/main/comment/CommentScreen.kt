package com.razzaghi.shopingbykmp.presentation.ui.main.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import kotlinx.coroutines.flow.Flow
import com.razzaghi.shopingbykmp.presentation.component.AddCommentDialog
import com.razzaghi.shopingbykmp.presentation.component.DefaultScreenUI
import com.razzaghi.shopingbykmp.presentation.theme.BorderColor
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentState
import com.razzaghi.shopingbykmp.presentation.ui.main.detail.CommentBox
import org.jetbrains.compose.resources.stringResource
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.comments
import shoping_by_kmp.shared.generated.resources.no_comments


@Composable
fun CommentScreen(
    state: CommentState,
    errors: Flow<UIComponent>,
    events: (CommentEvent) -> Unit,
    popup: () -> Unit
) {

    if (state.addCommentDialogState == UIComponentState.Show) {
        AddCommentDialog(onDismissRequest = {
            events(CommentEvent.OnUpdateAddCommentDialogState(UIComponentState.Hide))
        },
            onExecute = { rate, comment ->
                events(CommentEvent.AddComment(rate = rate, comment = comment))
            })
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CommentEvent.OnRetryNetwork) },
        titleToolbar = stringResource(Res.string.comments),
        startIconToolbar = Icons.AutoMirrored.Filled.ArrowBack,
        onClickStartIconToolbar = popup,
        endIconToolbar = Icons.Filled.AddComment,
        onClickEndIconToolbar = { events(CommentEvent.OnUpdateAddCommentDialogState(UIComponentState.Show)) }) {
        Column(modifier = Modifier.fillMaxSize()) {


            if (state.comments.isEmpty()) {
                Text(
                    stringResource(Res.string.no_comments),
                    style = MaterialTheme.typography.titleLarge,
                    color = BorderColor,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }


            LazyColumn {
                items(state.comments, key = { it.id }) {
                    CommentBox(comment = it, modifier = Modifier.fillMaxWidth())
                }
            }

        }
    }
}

