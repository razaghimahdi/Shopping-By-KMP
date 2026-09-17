package com.razzaghi.shopingbykmp.presentation.ui.main.checkout.view_model

import com.razzaghi.shopingbykmp.business.core.ViewSingleAction

sealed class CheckoutAction : ViewSingleAction {

    sealed class Navigation : CheckoutAction() {
        data object PopUp : Navigation()
    }

}