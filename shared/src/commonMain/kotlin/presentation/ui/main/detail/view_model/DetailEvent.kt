package presentation.ui.main.detail.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class DetailEvent {


    data class Like(val id: Long) : DetailEvent()
    data class AddBasket(val id: Long) : DetailEvent()

    data class OnUpdateSelectedImage(
        val value: String
    ) : DetailEvent()

    data class GetProduct(
        val id: Long
    ) : DetailEvent()

   data object OnRemoveHeadFromQueue : DetailEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : DetailEvent()

   data object OnRetryNetwork : DetailEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : DetailEvent()
}
