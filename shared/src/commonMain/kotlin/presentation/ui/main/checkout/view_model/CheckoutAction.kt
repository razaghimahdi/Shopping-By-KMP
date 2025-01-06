package presentation.ui.main.checkout.view_model

import business.core.ViewSingleAction

sealed class CheckoutAction : ViewSingleAction {

    sealed class Navigation : CheckoutAction() {
        data object PopUp : Navigation()
    }

}