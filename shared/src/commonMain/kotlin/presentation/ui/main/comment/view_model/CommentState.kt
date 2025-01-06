package presentation.ui.main.comment.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.core.ViewState
import business.domain.main.Comment

data class CommentState(
    val productId: Long = 0,
    val comments: List<Comment> = listOf(),
    val addCommentDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
