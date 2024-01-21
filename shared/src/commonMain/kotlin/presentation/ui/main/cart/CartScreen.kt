package presentation.ui.main.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import business.domain.main.Basket
import business.domain.main.Product
import business.domain.main.product_sample
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_4dp
import presentation.component.noRippleClickable
import presentation.component.rememberCustomImagePainter
import presentation.ui.main.cart.view_model.CartEvent
import presentation.ui.main.cart.view_model.CartState
import presentation.ui.main.detail.BuyButtonBox
import presentation.ui.main.detail.view_model.DetailEvent


@Composable
fun CartScreen(
    state: CartState,
    events: (CartEvent) -> Unit,
    navigateToDetail: (Int) -> Unit,
    navigateToCheckout: () -> Unit,
) {


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(CartEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CartEvent.OnRetryNetwork) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize().align(Alignment.Center)) {
                items(state.baskets) {
                    CartBox(it, navigateToDetail = navigateToDetail) {
                        events(CartEvent.DeleteFromBasket(it.id))
                    }
                }
            }

            if (state.baskets.isNotEmpty()) {
                Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                    ProceedButtonBox(
                        state.totalCost
                    ) {
                        navigateToCheckout()
                    }
                }
            }


        }
    }
}

@Composable
fun ProceedButtonBox(totalCost: String, onClick: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total Cost", style = MaterialTheme.typography.titleMedium)
                Text(totalCost, style = MaterialTheme.typography.titleLarge)
            }

            Spacer_16dp()

            DefaultButton(
                modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE),
                text = "Proceed to Checkout"
            ) {
                onClick()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBox(basket: Basket, navigateToDetail: (Int) -> Unit, deleteFromBasket: () -> Unit) {
    var show by remember { mutableStateOf(true) }

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                deleteFromBasket()
                show = false
                true
            } else false
        }
    )

    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                DismissCartContent(basket, navigateToDetail = navigateToDetail)
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissCartContent(basket: Basket, navigateToDetail: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier.fillMaxHeight().padding(vertical = 16.dp)
                    .weight(.3f)
                    .clip(MaterialTheme.shapes.small)
                    .noRippleClickable {
                        navigateToDetail(basket.product.id)
                    }
            ) {
                Image(
                    painter = rememberCustomImagePainter(basket.product.image),
                    null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer_16dp()

            Column(modifier = Modifier.weight(.4f)) {
                Text(
                    basket.product.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer_4dp()
                Text(
                    basket.product.category.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer_4dp()
                Text(
                    basket.product.getPrice(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxHeight()
                    .weight(.3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.size(25.dp),
                    shape = MaterialTheme.shapes.small,
                    onClick = {},
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("-",)
                    }
                }
                Spacer_4dp()
                Text(basket.count.toString())
                Spacer_4dp()
                Card(
                    modifier = Modifier.size(25.dp),
                    shape = MaterialTheme.shapes.small,
                    onClick = {},
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "+",
                        )
                    }
                }

            }
        }
        Divider(modifier = Modifier.fillMaxWidth())
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = MaterialTheme.colorScheme.primary.copy(alpha = .2f)
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        if (direction == DismissDirection.EndToStart) Icon(
            Icons.Default.Delete,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
    }
}

