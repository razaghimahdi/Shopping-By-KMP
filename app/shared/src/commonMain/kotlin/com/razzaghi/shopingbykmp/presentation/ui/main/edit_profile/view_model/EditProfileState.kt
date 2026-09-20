package com.razzaghi.shopingbykmp.presentation.ui.main.edit_profile.view_model

import com.razzaghi.shopingbykmp.business.core.NetworkState
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.Queue
import com.razzaghi.shopingbykmp.business.core.UIComponent
import com.razzaghi.shopingbykmp.business.core.UIComponentState
import com.razzaghi.shopingbykmp.business.core.ViewState

data class EditProfileState(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val image: String = "",
    val imageOptionDialog: UIComponentState = UIComponentState.Hide,
    val permissionDialog: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
) : ViewState
