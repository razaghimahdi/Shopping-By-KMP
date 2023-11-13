package presentation.ui.main.detail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import presentation.component.DefaultScreenUI
import presentation.component.Spacer_16dp
import presentation.component.Spacer_32dp
import presentation.component.Spacer_8dp
import presentation.component.noRippleClickable
import presentation.component.rememberCustomImagePainter
import presentation.theme.BackgroundContent
import presentation.theme.orange_400


private val imageList = listOf(
    "https://thumblr.uniid.it/product/184401/802183c05c13.jpg",
    "https://scene7.zumiez.com/is/image/zumiez/image/Nike-Essential-White-T-Shirt-_329802.jpg",
    "https://www.nepal.ubuy.com/productimg/?image=aHR0cHM6Ly9pNS53YWxtYXJ0aW1hZ2VzLmNvbS9hc3IvNzQwNTU4ZjgtMTQ0ZC00ZGIyLTgzMTgtYjlmMDY5YmJiNDFlLjc3NDNlZTY2ZTI0NGQxM2ZlMjhhYWEyZmRmMzdjNTE2LmpwZWc.jpg",
    "https://i.ebayimg.com/images/g/TYsAAOSwzHdfwBTi/s-l400.jpg",
    "https://cdn.idealo.com/folder/Product/200245/1/200245140/s4_produktbild_gross_19/nike-t-shirt-sportswear-essential-bv6169.jpg",
    "https://cms.brnstc.de/product_images/680x930_retina/cpro/media/images/product/23/5/100143057613000_0_1683184689767.jpg",
    "https://www.junior.shop/media/image/product/38605/lg/t-shirts-nike-t-shirt-mit-logo-marine-104-110-86e765-695.jpg",
)

@Composable
fun DetailScreen() {

    var selectedImage by remember { mutableStateOf(imageList.first()) }

    DefaultScreenUI() {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {

                Image(
                    painter = rememberCustomImagePainter(selectedImage),
                    null, modifier = Modifier.fillMaxSize()
                )

                Box(modifier = Modifier.padding(16.dp).align(Alignment.TopStart)) {
                    Card(
                        modifier = Modifier.size(55.dp).padding(4.dp),
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                }

                Box(modifier = Modifier.padding(16.dp).align(Alignment.TopEnd)) {
                    Card(
                        modifier = Modifier.size(55.dp).padding(4.dp),
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.FavoriteBorder, null)
                        }
                    }
                }

                Box(
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(16.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(imageList) {
                                ImageSliderBox(it) {
                                    selectedImage = it
                                }
                            }
                        }
                    }
                }


            }

            Spacer_32dp()

            Column(modifier = Modifier.padding(16.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Clothes", style = MaterialTheme.typography.titleMedium)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Filled.Star, null, tint = orange_400)
                        Text("4.5", style = MaterialTheme.typography.titleMedium)
                    }
                }


                Spacer_16dp()


                Text("Shirt model-231", style = MaterialTheme.typography.headlineLarge)

                Spacer_16dp()

                Text("Product Details", style = MaterialTheme.typography.titleLarge)

                Spacer_8dp()

                Text(
                    text = buildAnnotatedString {
                        append(
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. "
                        )
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Read More")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer_16dp()

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = BackgroundContent
                )


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
