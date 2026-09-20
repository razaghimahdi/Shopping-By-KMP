package com.razzaghi.shopingbykmp.presentation.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.razzaghi.shopingbykmp.presentation.SharedViewModel
import com.razzaghi.shopingbykmp.presentation.util.ChangeStatusBarColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import org.koin.compose.koinInject
import com.razzaghi.shopingbykmp.presentation.navigation.SplashNavigation
import com.razzaghi.shopingbykmp.presentation.ui.splash.LoginScreen
import com.razzaghi.shopingbykmp.presentation.ui.splash.RegisterScreen
import com.razzaghi.shopingbykmp.presentation.ui.splash.SplashScreen
import com.razzaghi.shopingbykmp.presentation.ui.splash.view_model.LoginAction
import com.razzaghi.shopingbykmp.presentation.ui.splash.view_model.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SplashNav(navigateToMain: () -> Unit) {
    val navigator = rememberNavController()
    val viewModel = koinViewModel<LoginViewModel>()

    LaunchedEffect(viewModel) {
        delay(4000L)
        viewModel.action.onEach { effect ->
            when (effect) {
                LoginAction.Navigation.NavigateToMain -> {
                    navigateToMain()
                }

                LoginAction.Navigation.NavigateToLogin -> {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login)
                }

            }
        }.collect {}
    }


    ChangeStatusBarColors(MaterialTheme.colorScheme.primary)
    NavHost(
        startDestination = SplashNavigation.Splash,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<SplashNavigation.Splash> {
            SplashScreen()
        }
        composable<SplashNavigation.Login> {
            LoginScreen(
                navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register)
                },
                errors = viewModel.errors,
                state = viewModel.state.value,
                events = { event -> viewModel.setEvent(event) }
            )
        }
        composable<SplashNavigation.Register> {
            RegisterScreen(
                popUp = {
                    navigator.popBackStack()
                }, state = viewModel.state.value,
                errors = viewModel.errors,
                events = { event -> viewModel.setEvent(event) }
            )
        }
    }

}