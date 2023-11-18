package presentation.ui.main.detail.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class DetailEvent {


    data class OnUpdateSelectedImage(
        val value: String
    ) : DetailEvent()

    data class GetProduct(
        val id: Int
    ) : DetailEvent()

    object OnRemoveHeadFromQueue : DetailEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : DetailEvent()

    object OnRetryNetwork : DetailEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : DetailEvent()
}
