package presentation.ui.main.comment.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.AddCommentInteractor
import business.interactors.main.GetCommentsInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CommentViewModel(
    private val getCommentsInteractor: GetCommentsInteractor,
    private val addCommentInteractor: AddCommentInteractor,
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
        addCommentInteractor.execute(
            productId = state.value.productId, rate = rate, comment = comment
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        if (it) onTriggerEvent(CommentEvent.GetComments)
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getComments() {
        getCommentsInteractor.execute(state.value.productId).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(CommentEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(comments = it) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
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