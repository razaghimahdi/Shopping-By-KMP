package presentation.ui.main.home

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import business.constants.Sort
import business.core.UIComponent
import business.domain.main.Category
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultScreenUI
import presentation.component.ProductBox
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.component.rememberCustomImagePainter
import presentation.theme.BorderColor
import presentation.theme.DefaultCardColorsTheme
import presentation.theme.PagerDotColor
import presentation.ui.main.home.view_model.HomeEvent
import presentation.ui.main.home.view_model.HomeState
import shoping_by_kmp.shared.generated.resources.Res
import shoping_by_kmp.shared.generated.resources.bell
import shoping_by_kmp.shared.generated.resources.category
import shoping_by_kmp.shared.generated.resources.flash_sale
import shoping_by_kmp.shared.generated.resources.location
import shoping_by_kmp.shared.generated.resources.most_sale
import shoping_by_kmp.shared.generated.resources.newest_products
import shoping_by_kmp.shared.generated.resources.search
import shoping_by_kmp.shared.generated.resources.see_all
import shoping_by_kmp.shared.generated.resources.setting
import shoping_by_kmp.shared.generated.resources.special_for_you

@Composable
fun HomeScreen(
    navigateToDetail: (Long) -> Unit = {},
    state: HomeState,
    events: (HomeEvent) -> Unit = {},
    errors: Flow<UIComponent>,
    navigateToNotifications: () -> Unit = {},
    navigateToSetting: () -> Unit = {},
    navigateToCategories: () -> Unit = {},
    navigateToSearch: (Long?, Int?) -> Unit = { _, _ -> },
) {
    val pagerState = rememberPagerState { state.home.banners.size }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(HomeEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {

                Spacer_8dp()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            stringResource(Res.string.location),
                            style = MaterialTheme.typography.labelSmall,
                        )
                        Spacer_4dp()
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painterResource(Res.drawable.location),
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                state.home.address.getLocation(),
                                style = MaterialTheme.typography.labelMedium,
                            )
                            Icon(
                                Icons.Filled.KeyboardArrowDown,
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )

                        }
                    }
                    Box(
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.primary.copy(.2f),
                            CircleShape
                        ).size(45.dp).noRippleClickable { navigateToNotifications() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painterResource(Res.drawable.bell),
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer_32dp()


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(.8f).height(DEFAULT__BUTTON_SIZE)
                            .border(1.dp, BorderColor, MaterialTheme.shapes.small)
                            .noRippleClickable { navigateToSearch(null, null) }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painterResource(Res.drawable.search),
                                null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer_4dp()
                            Text(
                                stringResource(Res.string.search),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.shapes.small
                            ).noRippleClickable { navigateToSetting() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painterResource(Res.drawable.setting),
                            null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }


            }

            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        stringResource(Res.string.special_for_you),
                        style = MaterialTheme.typography.titleLarge
                    )
                    /* Text(
                         "See All",
                         style = MaterialTheme.typography.labelMedium,
                         color = MaterialTheme.colorScheme.primary
                     )*/
                }


                HorizontalPager(
                    state = pagerState,
                    verticalAlignment = Alignment.CenterVertically
                ) { page ->
                    BannerImage(state.home.banners.getOrNull(page)?.banner ?: "")
                }



                Spacer_8dp()

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    DotsIndicator(
                        totalDots = state.home.banners.size,
                        selectedIndex = pagerState.currentPage,
                        dotSize = 8.dp
                    )
                }

                Spacer_16dp()


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(Res.string.category),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        stringResource(Res.string.see_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.noRippleClickable {
                            navigateToCategories()
                        }
                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.categories, key = { it.id }) {
                        CategoryBox(category = it) {
                            navigateToSearch(it.id, null)
                        }
                    }
                }



                Spacer_16dp()


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            stringResource(Res.string.flash_sale),
                            style = MaterialTheme.typography.titleLarge
                        )
                        TimerBox(state = state)
                    }
                    /* Text(
                         "See All",
                         style = MaterialTheme.typography.labelMedium,
                         color = MaterialTheme.colorScheme.primary
                     )*/
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(state.home.flashSale.products, key = { it.id }) {
                        ProductBox(product = it, onLikeClick = {
                            events(HomeEvent.Like(it.id))
                        }) { navigateToDetail(it.id) }
                    }
                }



                Spacer_16dp()


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(Res.string.most_sale),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        stringResource(Res.string.see_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.noRippleClickable {
                            navigateToSearch(null, Sort.MOST_SALE)
                        }
                    )
                }


                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.mostSale, key = { it.id }) {
                        ProductBox(product = it, onLikeClick = {
                            events(HomeEvent.Like(it.id))
                        }) { navigateToDetail(it.id) }
                    }
                }




                Spacer_16dp()


                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(Res.string.newest_products),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        stringResource(Res.string.see_all),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.noRippleClickable {
                            navigateToSearch(null, Sort.NEWEST)
                        }
                    )
                }


                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(state.home.newestProduct, key = { it.id }) {
                        ProductBox(product = it, onLikeClick = {
                            events(HomeEvent.Like(it.id))
                        }) { navigateToDetail(it.id) }
                    }
                }

            }
        }
    }
}


@Composable
fun TimerBox(state: HomeState) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                MaterialTheme.shapes.small
            )
                .padding(4.dp)
        ) {
            Text(
                state.time.hour.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            ":",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium
        )
        Box(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                MaterialTheme.shapes.small
            )
                .padding(4.dp)
        ) {
            Text(
                state.time.minute.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        Text(
            ":",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelMedium
        )

        Box(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.primary.copy(alpha = .2f),
                MaterialTheme.shapes.small
            )
                .padding(4.dp)
        ) {
            Text(
                state.time.second.toString(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}

@Composable
private fun CategoryBox(category: Category, onCategoryClick: () -> Unit) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(75.dp).noRippleClickable {
                onCategoryClick()
            }
        ) {
            Box(
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.primary.copy(.2f),
                    CircleShape
                ).size(60.dp)
                    .padding(12.dp)
            ) {

                AsyncImage(
                    category.icon, null,
                    modifier = Modifier.fillMaxSize().size(55.dp),
                    contentScale = ContentScale.Crop
                )
                /*  Image(
                      painter = rememberAsyncImagePainter(category.icon),
                      null,
                      modifier = Modifier.fillMaxSize().size(55.dp),
                      contentScale = ContentScale.Crop
                  )*/
            }
            Spacer_8dp()
            Text(
                category.name,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BannerImage(it: String) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Card(
            colors = DefaultCardColorsTheme(),
            modifier = Modifier.height(160.dp).fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = rememberCustomImagePainter(it),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}


@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = PagerDotColor,
    dotSize: Dp = 8.dp
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        for (index in 0 until totalDots) {

            val color by remember(selectedIndex) { derivedStateOf { Animatable(unSelectedColor) } }
            val size by animateDpAsState(if (index == selectedIndex) 20.dp else dotSize)


            LaunchedEffect(selectedIndex) {


                color.animateTo(
                    if (index == selectedIndex) selectedColor else unSelectedColor,
                    animationSpec = tween(300)
                )

            }

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Dot(size = size, color = color.value, shape = RoundedCornerShape(16.dp))
            }
        }
    }


}


@Composable
private fun Dot(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    size: Dp = 8.dp,
    color: Color
) {
    Box(
        modifier = modifier
            .padding(horizontal = 3.dp)
            .height(8.dp)
            .width(size)
            .clip(shape)
            .background(color)
    )
}
