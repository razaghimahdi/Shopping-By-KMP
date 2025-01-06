package presentation.ui.main.edit_profile.view_model

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.GetEmailFromCacheInteractor
import business.interactors.main.GetProfileInteractor
import business.interactors.main.UpdateProfileInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EditProfileViewModel(
    private val updateProfileInteractor: UpdateProfileInteractor,
    private val getProfileInteractor: GetProfileInteractor,
    private val getEmailFromCacheInteractor: GetEmailFromCacheInteractor,
) : BaseViewModel<EditProfileEvent, EditProfileState, Nothing>() {

    override fun setInitialState() = EditProfileState()

    override fun onTriggerEvent(event: EditProfileEvent) {
        when (event) {

            is EditProfileEvent.OnUpdateImageOptionDialog -> {
                onUpdateImageOptionDialog(event.value)
            }

            is EditProfileEvent.UpdateProfile -> {
                updateProfile(event.imageBitmap)
            }

            is EditProfileEvent.OnUpdatePermissionDialog -> {
                onUpdatePermissionDialog(event.value)
            }

            is EditProfileEvent.OnUpdateName -> {
                onUpdateName(event.value)
            }

            is EditProfileEvent.OnUpdateAge -> {
                onUpdateAge(event.value)
            }

            is EditProfileEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is EditProfileEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    init {
        getProfile()
        getEmail()
    }

    private fun updateProfile(imageBitmap: ImageBitmap?) {
        updateProfileInteractor.execute(
            name = state.value.name,
            age = state.value.age,
            image = imageBitmap
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(EditProfileEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {}

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateImageOptionDialog(value: UIComponentState) {
        setState { copy(imageOptionDialog = value) }
    }

    private fun onUpdatePermissionDialog(value: UIComponentState) {
        setState { copy(permissionDialog = value) }
    }

    private fun onUpdateName(value: String) {
        setState { copy(name = value) }
    }

    private fun onUpdateAge(value: String) {
        setState { copy(age = value) }
    }

    private fun getProfile() {
        getProfileInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(EditProfileEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(name = it.name, image = it.profileUrl, age = it.age) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getEmail() {
        getEmailFromCacheInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(EditProfileEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(email = it) }
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