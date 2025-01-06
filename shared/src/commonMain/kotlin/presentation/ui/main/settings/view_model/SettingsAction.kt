package presentation.ui.main.settings.view_model

import business.core.ViewSingleAction

sealed class SettingsAction : ViewSingleAction {

    sealed class Navigation : SettingsAction() {
        data object PopUp : Navigation()
    }

}