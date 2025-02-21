package presentation.ui.main.address.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.ViewState
import business.domain.main.Address

data class AddressState(
    val addresses: List<Address> = listOf(),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
