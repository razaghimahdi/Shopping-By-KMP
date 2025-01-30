package presentation.ui.main.edit_profile.view_model

import androidx.compose.ui.graphics.ImageBitmap
import business.core.BaseViewModel
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.GetEmailFromCacheUseCase
import business.interactors.main.GetProfileUseCase
import business.interactors.main.UpdateProfileUseCase

class EditProfileViewModel(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getEmailFromCacheUseCase: GetEmailFromCacheUseCase,
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
        executeUseCase(updateProfileUseCase.execute(
            UpdateProfileUseCase.Params(
                name = state.value.name,
                age = state.value.age,
                image = imageBitmap
            )
        ), onSuccess = {}, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(EditProfileEvent.OnUpdateNetworkState(it))
        }
        )
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
        executeUseCase(getProfileUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(name = it.name, image = it.profileUrl, age = it.age) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(EditProfileEvent.OnUpdateNetworkState(it))
        }
        )
    }


    private fun getEmail() {
        executeUseCase(getEmailFromCacheUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(email = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(EditProfileEvent.OnUpdateNetworkState(it))
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