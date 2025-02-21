package presentation.ui.main.address.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.core.UIComponentState
import business.interactors.main.AddAddressUseCase
import business.interactors.main.GetAddressesUseCase

class AddressViewModel(
    private val getAddressesUseCase: GetAddressesUseCase,
) : BaseViewModel<AddressEvent, AddressState, Nothing>() {

    override fun setInitialState() = AddressState()

    override fun onTriggerEvent(event: AddressEvent) {
        when (event) {


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

    private fun onRetryNetwork() {
        getAddresses()
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}