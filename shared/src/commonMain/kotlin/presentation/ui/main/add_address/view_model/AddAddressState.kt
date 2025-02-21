package presentation.ui.main.add_address.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.ViewState

data class AddAddressState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val country: String = "",
    val zipCode: String = "",

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
