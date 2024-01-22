package presentation.ui.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import business.domain.main.Product
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.CircleButton
import presentation.component.DefaultScreenUI
import presentation.component.FilterDialog
import presentation.component.SortDialog
import presentation.component.Spacer_16dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.component.rememberCustomImagePainter
import presentation.theme.Grey
import presentation.theme.TextFieldWithTransparentTheme
import presentation.ui.main.cart.CartBox
import presentation.ui.main.cart.view_model.CartEvent
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchState
import presentation.ui.main.wishlist.view_model.WishlistEvent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun SearchScreen(
    state: SearchState,
    events: (SearchEvent) -> Unit,
    navigateToDetailScreen: (Int) -> Unit,
    popUp: () -> Unit
) {

    if (state.filterDialogState == UIComponentState.Show) {
        FilterDialog(state = state, events = events)
    }

    if (state.sortDialogState == UIComponentState.Show) {
        SortDialog(state = state, events = events)
    }


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(SearchEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(SearchEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {


            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                CircleButton(
                    imageVector = Icons.Filled.ArrowBack,
                    onClick = { popUp() }
                )
                Spacer_8dp()
                SearchBox(
                    value = state.searchText,
                    onValueChange = { events(SearchEvent.OnUpdateSearchText(it)) },
                    onSearchExecute = { events(SearchEvent.Search()) })
            }
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                TextButton(onClick = {
                    events(
                        SearchEvent.OnUpdateSortDialogState(
                            UIComponentState.Show
                        )
                    )
                }, modifier = Modifier.weight(5f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painterResource("sort.xml"),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Sort")
                    }
                }
                TextButton(onClick = {
                    events(
                        SearchEvent.OnUpdateFilterDialogState(
                            UIComponentState.Show
                        )
                    )
                }, modifier = Modifier.weight(5f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            painterResource("filter.xml"),
                            null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text("Filter")
                    }
                }
            }

            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(state.search.products, key = {
                        it.id
                    }) {
                        ProductSearchBox(
                            it,
                            isLastItem = state.search.products.last() == it,
                            navigateToDetail = { navigateToDetailScreen(it.id) })
                    }
                }
            }

        }
    }
}

@Composable
private fun ProductSearchBox(product: Product, isLastItem: Boolean, navigateToDetail: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .noRippleClickable { navigateToDetail() }) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberCustomImagePainter(product.image),
                    null, modifier = Modifier.size(90.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer_16dp()
                Column() {
                    Text(
                        product.title,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer_4dp()
                    Text(
                        product.category.name,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer_4dp()
                    Text(
                        product.getPrice(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (!isLastItem) Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp))
        }
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchExecute: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .fillMaxWidth().height(55.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource("search.xml"),
                null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp).size(30.dp)
                    .noRippleClickable {
                        onSearchExecute()
                        keyboardController?.hide()
                    }
            )
            TextField(
                placeholder = {
                    Text("Search...")
                },
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text,
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchExecute()
                        keyboardController?.hide()
                    },
                ),
                maxLines = 1,
                colors = TextFieldWithTransparentTheme(),
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.8f)
            )

            Icon(
                painterResource("close.xml"),
                null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp).size(30.dp).noRippleClickable {
                    onValueChange("")
                    keyboardController?.hide()
                },
            )
        }
    }
}
