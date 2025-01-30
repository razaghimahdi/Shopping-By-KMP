package presentation.ui.main.profile.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.interactors.main.GetProfileUseCase

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, Nothing>() {

    override fun setInitialState() = ProfileState()

    override fun onTriggerEvent(event: ProfileEvent) {
        when (event) {

            is ProfileEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is ProfileEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getProfile()
    }


    private fun getProfile() {
        executeUseCase(getProfileUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(profile = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(ProfileEvent.OnUpdateNetworkState(it))
        }
        )
    }

    private fun onRetryNetwork() {
        getProfile()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}