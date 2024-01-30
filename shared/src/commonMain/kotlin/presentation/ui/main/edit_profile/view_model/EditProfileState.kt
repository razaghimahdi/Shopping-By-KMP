package presentation.ui.main.edit_profile.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState

data class EditProfileState(
    val name :String = "",
    val age :String = "",
    val email :String = "",
    val image :String = "",
    val imageOptionDialog: UIComponentState = UIComponentState.Hide,
    val permissionDialog: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
