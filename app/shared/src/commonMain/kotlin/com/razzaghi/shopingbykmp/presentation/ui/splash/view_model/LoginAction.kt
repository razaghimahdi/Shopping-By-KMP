package com.razzaghi.shopingbykmp.presentation.ui.splash.view_model

import com.razzaghi.shopingbykmp.business.core.ViewSingleAction

sealed class LoginAction : ViewSingleAction {

   // data class HandleError(val uiComponent: UIComponent) : LoginAction()

    sealed class Navigation : LoginAction() {

        data object NavigateToMain : Navigation()

        data object NavigateToLogin : Navigation()

    }

}