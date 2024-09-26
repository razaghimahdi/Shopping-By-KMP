package presentation.ui.main.detail


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import presentation.navigation.DetailNavigation
import presentation.ui.main.comment.CommentScreen
import presentation.ui.main.comment.view_model.CommentEvent
import presentation.ui.main.comment.view_model.CommentViewModel
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel

@Composable
fun DetailNav(id: Int, popUp: () -> Unit) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = DetailNavigation.Detail.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = DetailNavigation.Detail.route) {

            val viewModel: DetailViewModel = koinInject()
            LaunchedEffect(id) {
                viewModel.onTriggerEvent(DetailEvent.GetProduct(id))
            }
            DetailScreen(state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = {
                    popUp()
                }, navigateToMoreComment = {
                    navigator.navigate(DetailNavigation.Comment.route.plus("/$it"))
                })
        }
        composable(route = DetailNavigation.Comment.route.plus("/{id}")) { backStackEntry ->

            val viewModel: CommentViewModel = koinInject()
            val argument = backStackEntry.arguments
            val id = argument?.getString("id")?.toIntOrNull()

            LaunchedEffect(id) {
                id?.let {
                    viewModel.onTriggerEvent(CommentEvent.OnUpdateProductId(id))
                    viewModel.onTriggerEvent(CommentEvent.GetComments)
                }
            }

            CommentScreen(state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                popup = {
                    navigator.popBackStack()
                })
        }
    }
}
