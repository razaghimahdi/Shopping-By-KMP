package presentation.ui.main.address.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.constants.CUSTOM_TAG
import business.core.DataState
import business.core.NetworkState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.interactors.main.AddAddressInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class AddressViewModel(
    private val getAddressesInteractor: GetAddressesInteractor,
    private val addAddressInteractor: AddAddressInteractor,
) : ViewModel() {




    val state: MutableState<AddressState> = mutableStateOf(AddressState())


    fun onTriggerEvent(event: AddressEvent) {
        when (event) {

            is AddressEvent.AddAddress -> {
                addAddress(
                    country = event.country,
                    address = event.address,
                    city = event.city,
                    state = event.state,
                    zipCode = event.zipCode
                )
            }

            is AddressEvent.OnUpdateAddAddressDialogState -> {
                onUpdateAddAddressDialogState(event.value)
            }

            is AddressEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is AddressEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is AddressEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is AddressEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        getAddresses()
    }


    private fun addAddress(
        country: String,
        address: String,
        city: String,
        state: String,
        zipCode: String,
    ) {
        addAddressInteractor.execute(
            address = address,
            country = country,
            city = city,
            state = state,
            zipCode = zipCode
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}

                is DataState.Response -> {
                    onTriggerEvent(AddressEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        if (it) getAddresses()
                    }
                }

                is DataState.Loading -> {
                    this.state.value =
                        this.state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAddresses() {
        getAddressesInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {
                    onTriggerEvent(AddressEvent.OnUpdateNetworkState(dataState.networkState))
                }
                is DataState.Response -> {
                    onTriggerEvent(AddressEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        state.value = state.value.copy(addresses = it)
                    }
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateAddAddressDialogState(value: UIComponentState) {
        state.value = state.value.copy(addAddressDialogState = value)
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
        getAddresses()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}