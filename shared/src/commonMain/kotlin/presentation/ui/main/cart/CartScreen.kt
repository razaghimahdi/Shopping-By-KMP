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
import androidx.compose.ui.unit.dp
import business.domain.main.Product
import business.domain.main.product_sample
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_4dp
import presentation.component.rememberCustomImagePainter
import presentation.ui.main.cart.view_model.CartEvent
import presentation.ui.main.cart.view_model.CartState


@Composable
fun CartScreen(
    state: CartState,
    events: (CartEvent) -> Unit,
    navigateToDetail: (Int) -> Unit
) {


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(CartEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(CartEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            Column(modifier = Modifier.fillMaxWidth()) {
                for (i in 1..10) {
                    CartBox(product_sample)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBox(product: Product) {
    var show by remember { mutableStateOf(true) }

    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart ) {
                show = false
                true
            } else false
        }
    )

    AnimatedVisibility(
        show,exit = fadeOut(spring())
    ) {
    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier,
        background = {
            DismissBackground(dismissState)
        },
        dismissContent = {
            DismissCartContent(product)
        })
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissCartContent(product: Product) {
    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp).height(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier.fillMaxHeight().padding(vertical = 16.dp)
                    .weight(.3f)
                    .clip(MaterialTheme.shapes.small)
            ) {
                Image(
                    painter = rememberCustomImagePainter(product.image),
                    null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer_16dp()

            Column(modifier = Modifier.weight(.4f)) {
                Text(product.title, style = MaterialTheme.typography.titleMedium)
                Spacer_4dp()
                Text(product.category.name, style = MaterialTheme.typography.bodySmall)
                Spacer_4dp()
                Text(product.getPrice(), style = MaterialTheme.typography.labelMedium)
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
                        Text(
                            "-",
                        )
                    }
                }
                Spacer_4dp()
                Text("1")
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

