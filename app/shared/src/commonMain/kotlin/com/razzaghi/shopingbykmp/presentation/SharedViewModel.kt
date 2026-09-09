package com.razzaghi.shopingbykmp.presentation

import androidx.lifecycle.ViewModel
import presentation.token_manager.TokenManager


class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel()