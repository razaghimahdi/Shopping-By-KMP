package presentation.ui.main.home

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import business.domain.main.Category
import com.seiko.imageloader.rememberImagePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.component.DEFAULT__BUTTON_SIZE
import presentation.component.DefaultScreenUI
import presentation.component.ProductBox
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_4dp
import presentation.component.Spacer_8dp
import presentation.component.rememberCustomImagePainter
import presentation.theme.BackgroundContent
import presentation.theme.IconColorGrey
import presentation.theme.PagerDotColor
import presentation.ui.main.home.view_model.HomeEvent
import presentation.ui.main.home.view_model.HomeState
import presentation.ui.splash.view_model.LoginEvent
import presentation.ui.splash.view_model.LoginState
import kotlin.reflect.KFunction1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    state: HomeState,
    events: (HomeEvent) -> Unit
) {


    var selectedIndex by remember { mutableStateOf(0) }


    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(HomeEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        onTryAgain = { events(HomeEvent.OnRetryNetwork) }
    ) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.fillMaxWidth().background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
                ).padding(16.dp)
            ) {

                Spacer_8dp()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "Location",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                        Spacer_4dp()
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Filled.LocationOn, null, tint = Color.White)
                            Text(
                                "New York, USA",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                            Icon(Icons.Filled.KeyboardArrowDown, null, tint = Color.White)

                        }
                    }
                    Box(
                        modifier = Modifier.background(
                            IconColorGrey.copy(.2f),
                            MaterialTheme.shapes.small
                        ).size(45.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Notifications, null, tint = Color.White)
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
                            .background(Color.White, MaterialTheme.shapes.small)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                Icons.Filled.Search, null, tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer_4dp()
                            Text("Search", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth().height(DEFAULT__BUTTON_SIZE)
                            .background(Color.White, MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.Settings, null, tint = MaterialTheme.colorScheme.primary)
                    }
                }


            }


            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("#SpecialForYou", style = MaterialTheme.typography.titleLarge)
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }


            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(state.home.banners) {
                    BannerImage(it.banner) {
                        selectedIndex = state.home.banners.indexOf(it)
                    }
                }
            }

            Spacer_8dp()

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                DotsIndicator(
                    totalDots = state.home.banners.size,
                    selectedIndex = selectedIndex,
                    dotSize = 8.dp
                )
            }

            Spacer_16dp()


            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Category", style = MaterialTheme.typography.titleLarge)
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(state.home.categories) {

                    CategoryBox(category = it)
                }
            }



            Spacer_16dp()


            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Flash Sale", style = MaterialTheme.typography.titleLarge)
                    TimerBox()
                }
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(state.home.flashSale.products) {
                    ProductBox(product = it) { navigateToDetail(it.id) }
                }
            }



            Spacer_16dp()


            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Most Sale", style = MaterialTheme.typography.titleLarge)
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }


            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(state.home.mostSale) {
                    ProductBox(product = it) { navigateToDetail(it.id) }
                }
            }




            Spacer_16dp()


            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Newest Products", style = MaterialTheme.typography.titleLarge)
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }


            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(state.home.newestProduct) {
                    ProductBox(product = it) { navigateToDetail(it.id) }
                }
            }

        }
    }
}


@Composable
fun TimerBox() {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier.background(BackgroundContent, MaterialTheme.shapes.small)
                .padding(4.dp)
        ) {
            Text(
                "02",
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
            modifier = Modifier.background(BackgroundContent, MaterialTheme.shapes.small)
                .padding(4.dp)
        ) {
            Text(
                "13",
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
            modifier = Modifier.background(BackgroundContent, MaterialTheme.shapes.small)
                .padding(4.dp)
        ) {
            Text(
                "54",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CategoryBox(category: Category) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(75.dp)
        ) {
            Box(
                modifier = Modifier.background(BackgroundContent, CircleShape).size(60.dp)
                    .padding(12.dp)
            ) {

                Image(
                    painter = rememberImagePainter(category.icon),
                    null,
                    modifier = Modifier.fillMaxSize().size(55.dp),
                    contentScale = ContentScale.Crop
                )
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BannerImage(it: String, onFocusedChanged: () -> Unit) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Card(
            modifier = Modifier.height(150.dp).width(300.dp)
                .focusable(true)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onFocusedChanged()
                    }
                }, shape = MaterialTheme.shapes.medium,
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
private fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
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
