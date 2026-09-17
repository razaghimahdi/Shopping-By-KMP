package com.razzaghi.shopingbykmp.presentation.ui.main.add_address.view_model

import com.razzaghi.shopingbykmp.business.core.ViewSingleAction

sealed class AddAddressAction : ViewSingleAction {

    sealed class Navigation : AddAddressAction() {
        data object PopUp : Navigation()
    }

}