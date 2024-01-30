package presentation.ui.main.edit_profile.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.interactors.main.GetEmailFromCacheInteractor
import business.interactors.main.GetProfileInteractor
import business.interactors.main.UpdateProfileInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class EditProfileViewModel(
    private val updateProfileInteractor: UpdateProfileInteractor,
    private val getProfileInteractor: GetProfileInteractor,
    private val getEmailFromCacheInteractor: GetEmailFromCacheInteractor,
) : ViewModel() {




    val state: MutableState<EditProfileState> = mutableStateOf(EditProfileState())


    fun onTriggerEvent(event: EditProfileEvent) {
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

            is EditProfileEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is EditProfileEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
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
                    onTriggerEvent(EditProfileEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {}

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateImageOptionDialog(value: UIComponentState) {
        state.value = state.value.copy(imageOptionDialog = value)
    }

    private fun onUpdatePermissionDialog(value: UIComponentState) {
        state.value = state.value.copy(permissionDialog = value)
    }

    private fun onUpdateName(value: String) {
        state.value = state.value.copy(name = value)
    }

    private fun onUpdateAge(value: String) {
        state.value = state.value.copy(age = value)
    }

    private fun getProfile() {
        getProfileInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(EditProfileEvent.OnUpdateNetworkState(dataState.networkState))
                }

                is DataState.Response -> {
                    onTriggerEvent(EditProfileEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(name = it.name)
                        state.value = state.value.copy(image = it.profileUrl)
                        state.value = state.value.copy(age = it.age)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
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
                    onTriggerEvent(EditProfileEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(email = it)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getProfile()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}