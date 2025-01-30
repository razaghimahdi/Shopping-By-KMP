package presentation.ui.main.address.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.AddAddressUseCase
import business.interactors.main.GetAddressesUseCase

class AddressViewModel(
    private val getAddressesUseCase: GetAddressesUseCase,
    private val addAddressUseCase: AddAddressUseCase,
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
        executeUseCase(addAddressUseCase.execute(
            AddAddressUseCase.Params(
                address = address,
                country = country,
                city = city,
                state = state,
                zipCode = zipCode
            )
        ), onSuccess = {
            it?.let {
                if (it) getAddresses()
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }
        )
    }

    private fun getAddresses() {
        executeUseCase(getAddressesUseCase.execute(Unit), onSuccess = {
            it?.let {
                setState { copy(addresses = it) }
            }
        }, onLoading = {
            setState { copy(progressBarState = it) }
        }, onNetworkStatus = {
            setEvent(AddressEvent.OnUpdateNetworkState(it))
        }
        )
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