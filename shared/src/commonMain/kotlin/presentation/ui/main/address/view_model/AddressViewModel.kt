package presentation.ui.main.address.view_model

import androidx.lifecycle.viewModelScope
import business.core.BaseViewModel
import business.core.DataState
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.AddAddressInteractor
import business.interactors.main.GetAddressesInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddressViewModel(
    private val getAddressesInteractor: GetAddressesInteractor,
    private val addAddressInteractor: AddAddressInteractor,
) : BaseViewModel<AddressEvent, AddressState, Nothing>() {

    override fun setInitialState() = AddressState()

    override fun onTriggerEvent(event: AddressEvent) {
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        if (it) getAddresses()
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
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
                    setError { dataState.uiComponent }
                }

                is DataState.Data -> {
                    dataState.data?.let {
                        setState { copy(addresses = it) }
                    }
                }

                is DataState.Loading -> {
                    setState { copy(progressBarState = dataState.progressBarState) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdateAddAddressDialogState(value: UIComponentState) {
        setState { copy(addAddressDialogState = value) }
    }

    private fun onRetryNetwork() {
        getAddresses()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}