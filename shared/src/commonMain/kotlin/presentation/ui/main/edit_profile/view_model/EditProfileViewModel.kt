package presentation.ui.main.edit_profile.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.domain.main.Home
import business.interactors.main.GetEmailFromCacheInteractor
import business.interactors.main.GetProfileInteractor
import business.interactors.main.HomeInteractor
import business.interactors.main.LikeInteractor
import business.interactors.splash.CheckTokenInteractor
import business.interactors.splash.LoginInteractor
import business.interactors.splash.RegisterInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import presentation.ui.main.address.view_model.AddressEvent
import presentation.ui.main.wishlist.view_model.WishlistEvent

class EditProfileViewModel(
    private val getProfileInteractor: GetProfileInteractor,
    private val getEmailFromCacheInteractor: GetEmailFromCacheInteractor,
) : ViewModel() {


    private val TAG = "AppDebug EditProfileViewModel"


    val state: MutableState<EditProfileState> = mutableStateOf(EditProfileState())


    fun onTriggerEvent(event: EditProfileEvent) {
        when (event) {

            is EditProfileEvent.OnUpdateName -> {
                onUpdateName(event.value)
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

    private fun onUpdateName(value: String) {
        state.value = state.value.copy(name = value)
    }

    init {
        getProfile()
        getEmail()
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
            println("${TAG}: onTriggerEvent:  ${(uiComponent as UIComponent.None).message}")
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
            println("${TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
        getProfile()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}