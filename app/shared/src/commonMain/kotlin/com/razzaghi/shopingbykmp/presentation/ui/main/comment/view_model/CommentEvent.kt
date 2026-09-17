package com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewEvent

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
