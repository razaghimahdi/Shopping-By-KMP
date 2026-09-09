package presentation.ui.main.detail.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState
import business.domain.main.Product

data class DetailState(
    val product: Product = Product(),
    val selectedImage: String = "",
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
