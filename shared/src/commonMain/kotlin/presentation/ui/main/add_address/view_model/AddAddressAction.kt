package presentation.ui.main.add_address.view_model

import business.core.ViewSingleAction

sealed class AddAddressAction : ViewSingleAction {

    sealed class Navigation : AddAddressAction() {
        data object PopUp : Navigation()
    }

}