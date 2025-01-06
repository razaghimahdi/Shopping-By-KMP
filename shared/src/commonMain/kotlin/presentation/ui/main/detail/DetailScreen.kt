package presentation.ui.main.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import business.core.UIComponent
import business.domain.main.Comment
import business.domain.main.Product
import kotlinx.coroutines.flow.Flow
import presentation.component.CircleButton
import presentation.component.CircleImage
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultButton
import presentation.component.DefaultScreenUI
import presentation.component.ExpandingText
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.component.rememberCustomImagePainter
import presentation.theme.BackgroundContent
import presentation.theme.BorderColor
import presentation.theme.DefaultCardColorsTheme
import presentation.theme.orange_400
import presentation.ui.main.detail.view_model.DetailEvent
import presentation.ui.main.detail.view_model.DetailState
import presentation.util.convertDate
import org.jetbrains.compose.resources.stringResource
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.product_details
import shoping_by_kmp.shared.generated.resources.read_some_comments
import shoping_by_kmp.shared.generated.resources.total_price
import shoping_by_kmp.shared.generated.resources.add_to_cart
import shoping_by_kmp.shared.generated.resources.more
import shoping_by_kmp.shared.generated.resources.no_comments


@Composable
fun DetailScreen(
    errors: Flow<UIComponent>,
    state: DetailState,
    events: (DetailEvent) -> Unit,
    popup: () -> Unit,
    navigateToMoreComment: (Long) -> Unit,
) {


    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(DetailEvent.OnRetryNetwork) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().align(Alignment.TopCenter)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 75.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {

                    Image(
                        painter = rememberCustomImagePainter(state.selectedImage),
                        null, modifier = Modifier.fillMaxSize()
                    )

                    Box(modifier = Modifier.padding(16.dp).align(Alignment.TopStart)) {
                        CircleButton(
                            modifier = Modifier.padding(4.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            onClick = { popup() })
                    }

                    Box(modifier = Modifier.padding(16.dp).align(Alignment.TopEnd)) {
                        CircleButton(
                            modifier = Modifier.padding(4.dp),
                            imageVector = if (state.product.isLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            onClick = {
                                events(DetailEvent.Like(state.product.id))
                            })
                    }

                    Box(
                        modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Card(
                            colors = DefaultCardColorsTheme(),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            shape = MaterialTheme.shapes.small
                        ) {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(state.product.gallery) {
                                    ImageSliderBox(it) {
                                        events(DetailEvent.OnUpdateSelectedImage(it))
                                    }
                                }
                            }
                        }
                    }


                }

                Spacer_32dp()

                Column(modifier = Modifier.padding(vertical = 16.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            state.product.category.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Filled.Star, null, tint = orange_400)
                            Text(
                                state.product.rate.toString(),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }


                    Spacer_16dp()


                    Text(
                        state.product.title,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer_16dp()

                    Text(
                        stringResource(Res.string.product_details),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer_8dp()

                    ExpandingText(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        text = state.product.description,
                        style = MaterialTheme.typography.bodySmall,
                    ) {}


                    Spacer_16dp()

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = BackgroundContent
                    )

                    Spacer_16dp()

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(Res.string.read_some_comments),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = stringResource(Res.string.more),
                            modifier = Modifier
                                .clickable {
                                    navigateToMoreComment(state.product.id)
                                },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )

                    }

                    Spacer_8dp()

                    if (state.product.comments.isEmpty()) {
                        Text(
                            stringResource(Res.string.no_comments),
                            style = MaterialTheme.typography.titleLarge,
                            color = BorderColor,
                            modifier = Modifier.padding(horizontal = 32.dp)
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 24.dp)
                    ) {
                        items(state.product.comments, key = { it.createAt }) {
                            CommentBox(comment = it)
                        }
                    }

                    Spacer_16dp()

                }
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()) {
                BuyButtonBox(
                    state.product
                ) {
                    events(DetailEvent.AddBasket(state.product.id))
                }
            }
        }
    }
}

@Composable
fun BuyButtonBox(product: Product, onClick: () -> Unit) {
    Card(
        colors = DefaultCardColorsTheme(),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(Res.string.total_price),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(product.getPrice(), style = MaterialTheme.typography.titleLarge)
            }

            DefaultButton(
                modifier = Modifier.fillMaxWidth(.7f).height(DEFAULT__BUTTON_SIZE),
                text = stringResource(Res.string.add_to_cart)
            ) {
                onClick()
            }
        }
    }
}

@Composable
fun CommentBox(comment: Comment, modifier: Modifier = Modifier.width(300.dp)) {
    Box(modifier = Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
        Card(
            colors = DefaultCardColorsTheme(),
            modifier = modifier.height(160.dp),
            elevation = CardDefaults.cardElevation(8.dp), shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircleImage(
                            image = comment.user.image
                        )
                        Spacer_4dp()
                        Text(comment.user.fetchName(), style = MaterialTheme.typography.titleSmall)
                    }
                    Text(
                        comment.createAt.convertDate(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer_8dp()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        comment.comment,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Star, null, tint = orange_400)
                        Text(comment.rate.toString(), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

@Composable
fun ImageSliderBox(it: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(65.dp).clip(MaterialTheme.shapes.small).padding(4.dp)
            .noRippleClickable { onClick() }) {
        Image(
            rememberCustomImagePainter(it),
            null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
