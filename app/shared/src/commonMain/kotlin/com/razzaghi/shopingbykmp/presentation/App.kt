package com.razzaghi.shopingbykmp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.razzaghi.shopingbykmp.di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import com.razzaghi.shopingbykmp.presentation.navigation.AppNavigation
import com.razzaghi.shopingbykmp.presentation.theme.AppTheme
import com.razzaghi.shopingbykmp.presentation.ui.main.MainNav
import com.razzaghi.shopingbykmp.presentation.ui.splash.SplashNav

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {

    KoinApplication(application = {
        modules(appModule())
    }) {


        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }

        AppTheme {
            val navigator = rememberNavController()
            val viewModel: SharedViewModel = koinInject()

            LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
                if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                    navigator.popBackStack()
                    navigator.navigate(AppNavigation.Splash)
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Splash,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable<AppNavigation.Splash> {
                        SplashNav(navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Main)
                        })
                    }
                    composable<AppNavigation.Main> {
                        MainNav() {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Splash)
                        }
                    }
                }
            }

        }
    }
}




