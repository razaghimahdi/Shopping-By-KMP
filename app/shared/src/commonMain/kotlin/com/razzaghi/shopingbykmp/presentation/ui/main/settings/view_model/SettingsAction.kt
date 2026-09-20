package com.razzaghi.shopingbykmp.presentation.ui.main.settings.view_model

import com.razzaghi.shopingbykmp.business.core.ViewSingleAction

sealed class SettingsAction : ViewSingleAction {

    sealed class Navigation : SettingsAction() {
        data object PopUp : Navigation()
    }

}