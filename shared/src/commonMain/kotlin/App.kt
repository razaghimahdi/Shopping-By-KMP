import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.BackHandler
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import theme.AppTheme

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun App() {
   PreComposeApp {
       val navigator = rememberNavigator()


       AppTheme {
           Scaffold() {

           }
       }
   }
}
