package presentation.ui.main.comment.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.AddCommentUseCase
import business.interactors.main.GetCommentsUseCase

class CommentViewModel(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase,
) : BaseViewModel<CommentEvent, CommentState, Nothing>() {

    override fun setInitialState() = CommentState()

    override fun onTriggerEvent(event: CommentEvent) {
        when (event) {

            is CommentEvent.AddComment -> {
                addComment(comment = event.comment, rate = event.rate)
            }

            is CommentEvent.OnUpdateAddCommentDialogState -> {
                onUpdateAddCommentDialogState(event.value)
            }

            is CommentEvent.GetComments -> {
                getComments()
            }

            is CommentEvent.OnUpdateProductId -> {
                onUpdateProductId(event.id)
            }

            is CommentEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is CommentEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    private fun onUpdateProductId(id: Long) {
        setState { copy(productId = id) }
    }


    private fun addComment(comment: String, rate: Double) {
        executeUseCase(
            addCommentUseCase.execute(
                AddCommentUseCase.Params(
                    productId = state.value.productId,
                    rate = rate,
                    comment = comment
                )
            ), onSuccess = {
                it?.let {
                    if (it) onTriggerEvent(CommentEvent.GetComments)
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }
        )
    }

    private fun getComments() {


        executeUseCase(
            getCommentsUseCase.execute(
                GetCommentsUseCase.Params(state.value.productId)
            ), onSuccess = {
                it?.let {
                    setState { copy(comments = it) }
                }
            }, onLoading = {
                setState { copy(progressBarState = it) }
            }, onNetworkStatus = {
                setEvent(CommentEvent.OnUpdateNetworkState(it))
            }
        )
    }

    private fun onUpdateAddCommentDialogState(value: UIComponentState) {
        setState { copy(addCommentDialogState = value) }
    }

    private fun onRetryNetwork() {
        onTriggerEvent(CommentEvent.GetComments)
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}