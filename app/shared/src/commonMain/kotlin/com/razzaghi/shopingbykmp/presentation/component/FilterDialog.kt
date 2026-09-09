package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    state: SearchState,
    events: (SearchEvent) -> Unit,
) {


    var selectedRange by remember {
        mutableStateOf(state.selectedRange)
    }

    val selectedCategories = state.selectedCategory.toMutableStateList()


    CustomAlertDialog(
        onDismissRequest = {
            events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
        },
        modifier = Modifier
            .fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Spacer_16dp()

            Text(
                "Filter",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer_32dp()

            Text(
                "Price:",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            RangeSlider(
                value = selectedRange,
                onValueChange = { selectedRange = it },
                valueRange = state.searchFilter.minPrice
                    .toFloat()..state.searchFilter.maxPrice.toFloat(),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "$${selectedRange.start.toInt()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    "$${selectedRange.endInclusive.toInt()}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer_16dp()

            Text(
                "Category:",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer_8dp()

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(state.searchFilter.categories, { it.id }) {
                    CategoryChipsBox(it, isSelected = selectedCategories.contains(it)) {
                        if (selectedCategories.contains(it)) {
                            selectedCategories.remove(it)
                        } else {
                            selectedCategories.add(it)
                        }
                    }
                }
            }

            Spacer_32dp()

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                DefaultButton(modifier = Modifier.weight(1f), text = "Reset") {
                    events(SearchEvent.OnUpdateSelectedCategory(listOf()))
                    events(SearchEvent.OnUpdatePriceRange(0f..10f))
                    events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
                    events(SearchEvent.Search())
                }
                Spacer_16dp()
                DefaultButton(modifier = Modifier.weight(1f), text = "Filter") {
                    events(SearchEvent.OnUpdateSelectedCategory(selectedCategories))
                    events(SearchEvent.OnUpdatePriceRange(selectedRange))
                    events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
                    events(
                        SearchEvent.Search(
                            minPrice = selectedRange.start.toInt(),
                            maxPrice = selectedRange.endInclusive.toInt(),
                            categories = selectedCategories
                        )
                    )
                }
            }

            Spacer_16dp()
        }

    }

}