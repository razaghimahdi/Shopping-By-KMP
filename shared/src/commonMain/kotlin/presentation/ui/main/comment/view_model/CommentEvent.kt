package presentation.ui.main.comment.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState

sealed class CommentEvent {

    data class OnUpdateAddCommentDialogState(val value: UIComponentState) : CommentEvent()

   data object GetComments : CommentEvent()

    data class OnUpdateProductId(val id: Int) : CommentEvent()

    data class AddComment(
        val rate: Double,
        val comment: String,
    ) : CommentEvent()

   data object OnRemoveHeadFromQueue : CommentEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : CommentEvent()

   data object OnRetryNetwork : CommentEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CommentEvent()
}
