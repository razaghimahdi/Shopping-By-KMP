package presentation.ui.main.profile.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.interactors.main.GetProfileInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProfileViewModel(
    private val getProfileInteractor: GetProfileInteractor,
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
        getProfileInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(ProfileEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(profile = it) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onRetryNetwork() {
        getProfile()
    }

    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}