package presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import common.Context
import di.appModule
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import presentation.navigation.AppNavigation
import presentation.theme.AppTheme
import presentation.ui.main.MainNav
import presentation.ui.splash.SplashNav

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun App(context: Context?) {

    KoinApplication(application = {
        modules(appModule(context))
    }) {


        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        AppTheme {

            val viewModel: SharedViewModel = koinInject()
            val backStack = rememberNavBackStack(
                SavedStateConfiguration.DEFAULT,
                AppNavigation.Splash
            )
            LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
                if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                    if (backStack.lastOrNull() != AppNavigation.Splash) {
                        backStack.clear()
                        backStack.add(AppNavigation.Splash)
                    }
                }
            }

            NavDisplay(
                backStack = backStack,
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    if (backStack.size > 1) {
                        backStack.removeAt(backStack.lastIndex)
                    }
                },
                entryProvider = { key ->
                    val destination = key as AppNavigation

                    when (destination) {
                        AppNavigation.Splash -> NavEntry(key) {
                            SplashNav(navigateToMain = {
                                backStack.clear()
                                backStack.add(AppNavigation.Main)
                            })
                        }

                        AppNavigation.Main -> NavEntry(key) {
                            MainNav(context = context, logout = {
                                backStack.clear()
                                backStack.add(AppNavigation.Splash)
                            })
                        }

                    }
                }
            )


        }
    }
}




