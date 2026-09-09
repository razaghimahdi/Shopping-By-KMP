package presentation.ui.main.categories.view_model

import business.core.NetworkState
import business.core.UIComponent
import business.core.ViewEvent

sealed class CategoriesEvent : ViewEvent {

    data object OnRetryNetwork : CategoriesEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CategoriesEvent()

}
