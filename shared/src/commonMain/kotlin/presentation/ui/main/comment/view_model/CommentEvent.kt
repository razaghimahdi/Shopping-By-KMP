package presentation.ui.main.comment.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.UIComponentState
import presentation.ui.main.home.view_model.HomeEvent

sealed class CommentEvent {

    data class OnUpdateAddCommentDialogState(val value: UIComponentState) : CommentEvent()

    object GetComments : CommentEvent()

    data class OnUpdateProductId(val id: Int) : CommentEvent()

    data class AddComment(
        val rate: Double,
        val comment: String,
    ) : CommentEvent()

    object OnRemoveHeadFromQueue : CommentEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : CommentEvent()

    object OnRetryNetwork : CommentEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CommentEvent()
}
