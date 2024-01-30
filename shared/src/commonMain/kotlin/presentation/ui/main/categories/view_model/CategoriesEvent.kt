package presentation.ui.main.categories.view_model

import business.core.NetworkState
import business.core.UIComponent

sealed class CategoriesEvent {


   data object OnRemoveHeadFromQueue : CategoriesEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : CategoriesEvent()

   data object OnRetryNetwork : CategoriesEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : CategoriesEvent()

}
