package presentation.ui.main.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.constants.PAGINATION_PAGE_SIZE
import business.core.ProgressBarState
import business.domain.main.Category
import business.domain.main.product_sample
import presentation.component.DefaultScreenUI
import presentation.component.ProductBox
import presentation.component.Spacer_8dp
import presentation.ui.main.wishlist.view_model.WishlistEvent
import presentation.ui.main.wishlist.view_model.WishlistState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WishlistScreen(
    state: WishlistState,
    events: (WishlistEvent) -> Unit,
    navigateToDetail: (Int) -> Unit
) {


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(WishlistEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(WishlistEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.wishlist.categories) {
                    CategoryChipsBox(category = it, isSelected = it == state.selectedCategory) {
                        events(WishlistEvent.OnUpdateSelectedCategory(it))
                    }
                }
            }

            Spacer_8dp()


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                content = {
                    itemsIndexed(state.wishlist.products){index, product ->

                        if ((index + 1) >= (state.page * PAGINATION_PAGE_SIZE) &&
                            state.progressBarState == ProgressBarState.Idle &&
                            state.hasNextPage
                        ) {
                            events(WishlistEvent.GetNextPage)
                        }

                        ProductBox(
                            product = product,
                            modifier = Modifier.fillMaxWidth(.5f),
                            onLikeClick = {
                                events(WishlistEvent.LikeProduct(product.id))
                            }
                        ) {
                            navigateToDetail(product.id)
                        }
                    }
                }
            )
/*

            FlowRow(modifier = Modifier.fillMaxWidth().padding(8.dp), maxItemsInEachRow = 2) {
                state.wishlist.products.forEach {
                    ProductBox(
                        product = it,
                        modifier = Modifier.fillMaxWidth(.5f),
                        onLikeClick = {
                            events(WishlistEvent.LikeProduct(it.id))
                        }) {
                        navigateToDetail(it.id)
                    }
                }
            }

*/

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChipsBox(category: Category, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(4.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(category.name, modifier = Modifier.padding(12.dp))
    }
}
