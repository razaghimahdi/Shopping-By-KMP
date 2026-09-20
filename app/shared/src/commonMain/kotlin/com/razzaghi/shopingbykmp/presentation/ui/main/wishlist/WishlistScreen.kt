package com.razzaghi.shopingbykmp.presentation.ui.main.wishlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaghi.shopingbykmp.business.constants.PAGINATION_PAGE_SIZE
import com.razzaghi.shopingbykmp.business.core.ProgressBarState
import com.razzaghi.shopingbykmp.business.core.UIComponent
import kotlinx.coroutines.flow.Flow
import com.razzaghi.shopingbykmp.presentation.component.CategoryChipsBox
import com.razzaghi.shopingbykmp.presentation.component.DefaultScreenUI
import com.razzaghi.shopingbykmp.presentation.component.ProductBox
import com.razzaghi.shopingbykmp.presentation.component.Spacer_8dp
import com.razzaghi.shopingbykmp.presentation.theme.BorderColor
import com.razzaghi.shopingbykmp.presentation.ui.main.wishlist.view_model.WishlistEvent
import com.razzaghi.shopingbykmp.presentation.ui.main.wishlist.view_model.WishlistState
import org.jetbrains.compose.resources.stringResource
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.wishlist_is_empty

@Composable
fun WishlistScreen(
    state: WishlistState,
    events: (WishlistEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToDetail: (Long) -> Unit
) {


    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(WishlistEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
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


            if (state.wishlist.products.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        stringResource(Res.string.wishlist_is_empty),
                        style = MaterialTheme.typography.labelLarge,
                        color = BorderColor,
                    )
                }
            }


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                contentPadding = PaddingValues(8.dp),
                content = {
                    itemsIndexed(state.wishlist.products) { index, product ->

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

        }
    }
}



