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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

val sampleBannerList = listOf<String>(
    "https://t4.ftcdn.net/jpg/04/95/28/65/360_F_495286577_rpsT2Shmr6g81hOhGXALhxWOfx1vOQBa.jpg",
    "https://img.freepik.com/free-vector/stylish-glowing-digital-red-lines-banner_1017-23964.jpg",
    "https://bbdniit.ac.in/wp-content/uploads/2020/09/banner-background-without-image-min.jpg",
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navigateToDetail: () -> Unit) {


    // val pagerState = rememberPagerState { sampleBannerList.size }
    var selectedIndex by remember { mutableStateOf(0) }


    DefaultScreenUI() {
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
                items(sampleBannerList) {
                    BannerImage(it) {
                        selectedIndex = sampleBannerList.indexOf(it)
                    }
                }
            }

            Spacer_8dp()

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                DotsIndicator(
                    totalDots = sampleBannerList.size,
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

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                CategoryBox(title = "Clothes", image = "shirt.xml")
                CategoryBox(title = "Computer", image = "pc.xml")
                CategoryBox(title = "Shoes", image = "shoes.xml")
                CategoryBox(title = "Watch", image = "watch.xml")
                CategoryBox(title = "Mobile", image = "phone.xml")
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

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                ProductBox(
                    title = "Nike model-934",
                    rate = "4.5",
                    isLike = true,
                    price = "$120.00",
                    image = "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/5b0981ff-45f8-40c3-9372-32430a62aaea/dunk-high-womens-shoes-PXHcGT.png",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Nike model-637",
                    rate = "3.4",
                    isLike = false,
                    price = "$180.00",
                    image = "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/019f60a9-aa10-4327-b5f6-b940b753fbdb/dunk-high-1985-shoes-L05QbB.png",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Shirt model-232",
                    rate = "2.4",
                    isLike = false,
                    price = "$99.00",
                    image = "https://thumblr.uniid.it/product/184401/802183c05c13.jpg",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Shirt model-131",
                    rate = "4.4",
                    isLike = true,
                    price = "$82.00",
                    image = "https://www.deinsportsfreund.de/media/image/product/399225/lg/nike-academy-pro-t-shirt-herren-schwarz-grau-weiss.jpg",
                ) { navigateToDetail() }
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

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {

                ProductBox(
                    title = "SmartWatch-113",
                    rate = "2.4",
                    isLike = false,
                    price = "$530.00",
                    image = "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MT653_VW_34FR+watch-49-titanium-ultra2_VW_34FR+watch-face-49-ocean-ultra2_VW_34FR_GEO_DE?wid=2000&hei=2000&fmt=png-alpha&.v=1694507270905",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Shirt model-131",
                    rate = "4.4",
                    isLike = true,
                    price = "$82.00",
                    image = "https://www.deinsportsfreund.de/media/image/product/399225/lg/nike-academy-pro-t-shirt-herren-schwarz-grau-weiss.jpg",
                ) { navigateToDetail() }
                ProductBox(
                    title = "SmartWatch-113",
                    rate = "4.5",
                    isLike = true,
                    price = "$999.00",
                    image = "https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/google-pixel-watch/hero-static/charcoal/google-pixel-watch-charcoal-device-3qt-left.png",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Nike model-637",
                    rate = "3.4",
                    isLike = false,
                    price = "$180.00",
                    image = "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/019f60a9-aa10-4327-b5f6-b940b753fbdb/dunk-high-1985-shoes-L05QbB.png",
                ) { navigateToDetail() }
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

            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {

                ProductBox(
                    title = "SmartWatch-113",
                    rate = "4.5",
                    isLike = true,
                    price = "$999.00",
                    image = "https://www.fitbit.com/global/content/dam/fitbit/global/pdp/devices/google-pixel-watch/hero-static/charcoal/google-pixel-watch-charcoal-device-3qt-left.png",
                ) { navigateToDetail() }
                ProductBox(
                    title = "SmartWatch-113",
                    rate = "2.4",
                    isLike = false,
                    price = "$530.00",
                    image = "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/MT653_VW_34FR+watch-49-titanium-ultra2_VW_34FR+watch-face-49-ocean-ultra2_VW_34FR_GEO_DE?wid=2000&hei=2000&fmt=png-alpha&.v=1694507270905",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Shirt model-131",
                    rate = "4.4",
                    isLike = true,
                    price = "$82.00",
                    image = "https://www.deinsportsfreund.de/media/image/product/399225/lg/nike-academy-pro-t-shirt-herren-schwarz-grau-weiss.jpg",
                ) { navigateToDetail() }
                ProductBox(
                    title = "Nike model-637",
                    rate = "3.4",
                    isLike = false,
                    price = "$180.00",
                    image = "https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/019f60a9-aa10-4327-b5f6-b940b753fbdb/dunk-high-1985-shoes-L05QbB.png",
                ) { navigateToDetail() }
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
fun CategoryBox(title: String, image: String) {
    Box(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.background(BackgroundContent, CircleShape).size(60.dp)
                    .padding(12.dp)
            ) {
                Icon(
                    painterResource(image),
                    null,
                    modifier = Modifier.fillMaxSize(),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer_8dp()
            Text(title, style = MaterialTheme.typography.labelMedium, maxLines = 1)
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
