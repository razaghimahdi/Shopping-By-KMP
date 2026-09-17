package com.razzaghi.shopingbykmp.presentation.ui.main.edit_profile.view_model

import androidx.compose.ui.graphics.ImageBitmap
import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewEvent

sealed class EditProfileEvent : ViewEvent {

    data class UpdateProfile(val imageBitmap: ImageBitmap?) : EditProfileEvent()

    data class OnUpdateImageOptionDialog(val value: UIComponentState) : EditProfileEvent()

    data class OnUpdatePermissionDialog(val value: UIComponentState) : EditProfileEvent()

    data class OnUpdateName(val value: String) : EditProfileEvent()

    data class OnUpdateAge(val value: String) : EditProfileEvent()

    data object OnRetryNetwork : EditProfileEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : EditProfileEvent()

}
