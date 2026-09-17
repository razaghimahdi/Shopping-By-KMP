package com.razzaghi.shopingbykmp.presentation

import androidx.lifecycle.ViewModel
import com.razzaghi.shopingbykmp.presentation.token_manager.TokenManager


class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel()