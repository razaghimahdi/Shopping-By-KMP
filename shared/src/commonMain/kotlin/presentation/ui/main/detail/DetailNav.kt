package presentation.ui.main.detail


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import presentation.navigation.DetailNavigation
import presentation.ui.main.comment.CommentScreen
import presentation.ui.main.comment.view_model.CommentEvent
import presentation.ui.main.comment.view_model.CommentViewModel
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailViewModel

@Composable
fun DetailNav(id: Int, popUp: () -> Unit) {
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = DetailNavigation.Detail.route,
    ) {
        scene(route = DetailNavigation.Detail.route) {

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
        scene(route = DetailNavigation.Comment.route.plus(DetailNavigation.Comment.objectPath)) { backStackEntry ->
            backStackEntry.path<Int>(DetailNavigation.Comment.objectName)?.let {

                val viewModel: CommentViewModel = koinInject()
                LaunchedEffect(it) {
                    viewModel.onTriggerEvent(CommentEvent.OnUpdateProductId(it))
                    viewModel.onTriggerEvent(CommentEvent.GetComments)
                }
                CommentScreen(state = viewModel.state.value,
                    events = viewModel::onTriggerEvent,
                    popup = {
                        navigator.popBackStack()
                    })
            }
        }
    }
}
