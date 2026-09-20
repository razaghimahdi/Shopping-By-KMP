package com.razzaghi.shopingbykmp.presentation.ui.main.detail


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.compose.koinInject
import com.razzaghi.shopingbykmp.presentation.navigation.DetailNavigation
import com.razzaghi.shopingbykmp.presentation.ui.main.address.view_model.AddressViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.CommentScreen
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.comment.view_model.CommentViewModel
import com.razzaghi.shopingbykmp.presentation.ui.main.detail.view_model.DetailEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.detail.view_model.DetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailNav(id: Long, popUp: () -> Unit) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = DetailNavigation.Detail,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<DetailNavigation.Detail> {
            val viewModel = koinViewModel<DetailViewModel>()
            LaunchedEffect(id) {
                viewModel.onTriggerEvent(DetailEvent.GetProduct(id))
            }
            DetailScreen(state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                popup = {
                    popUp()
                }, navigateToMoreComment = {
                    navigator.navigate(DetailNavigation.Comment(it))
                })
        }
        composable<DetailNavigation.Comment> { backStackEntry ->
            val viewModel = koinViewModel<CommentViewModel>()
            val argument = backStackEntry.toRoute<DetailNavigation.Comment>()
            val id = argument.id

            LaunchedEffect(id) {
                viewModel.onTriggerEvent(CommentEvent.OnUpdateProductId(id))
                viewModel.onTriggerEvent(CommentEvent.GetComments)
            }

            CommentScreen(state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                errors = viewModel.errors,
                popup = {
                    navigator.popBackStack()
                })
        }
    }
}
