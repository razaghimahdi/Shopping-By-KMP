package presentation.ui.main.comment.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import business.core.ViewEvent

sealed class CommentEvent : ViewEvent {

    data class OnUpdateAddCommentDialogState(val value: UIComponentState) : CommentEvent()

    data object GetComments : CommentEvent()

    data class OnUpdateProductId(val id: Long) : CommentEvent()

    data class AddComment(
        val rate: Double,
        val comment: String,
    ) : CommentEvent()

    data object OnRetryNetwork : CommentEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CommentEvent()
}
