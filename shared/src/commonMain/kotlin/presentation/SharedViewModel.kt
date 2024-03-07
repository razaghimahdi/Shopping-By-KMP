package presentation

import moe.tlaster.precompose.viewmodel.ViewModel
import presentation.token_manager.TokenManager

class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel()