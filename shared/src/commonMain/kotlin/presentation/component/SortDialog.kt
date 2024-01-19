package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.core.UIComponentState
import business.domain.main.Category
import business.domain.main.Search
import business.domain.main.SearchFilter
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDialog(
    state: SearchState,
    events: (SearchEvent) -> Unit,
) {

    val selectedSort = mutableStateOf(state.selectedSort)


    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background),
        onDismissRequest = {
            events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
        },
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {


            Text(
                "Sort",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer_16dp()

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(0))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == 0, onCheckedChange = {})
                Text("Newest", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(1))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == 1, onCheckedChange = {})
                Text("Oldest", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(2))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == 2, onCheckedChange = {})
                Text("Highest Price", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(3))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == 3, onCheckedChange = {})
                Text("Lowest Price", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(4))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == 4, onCheckedChange = {})
                Text("Most Sale", style = MaterialTheme.typography.labelLarge)
            }


        }

    }

}