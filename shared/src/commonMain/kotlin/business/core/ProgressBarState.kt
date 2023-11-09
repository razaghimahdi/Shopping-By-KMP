package business.core

sealed class ProgressBarState{

    object ButtonLoading: ProgressBarState()

    object ScreenLoading: ProgressBarState()

    object FullScreenLoading: ProgressBarState()

    object LoadingWithLogo: ProgressBarState()

    object Idle: ProgressBarState()

}

