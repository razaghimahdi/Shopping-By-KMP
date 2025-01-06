package presentation.ui.main.profile.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.ViewState
import business.domain.main.Profile

data class ProfileState(
    val profile: Profile = Profile(),

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
