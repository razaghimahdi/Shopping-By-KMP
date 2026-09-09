package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import business.constants.Sort.HIGHER_PRICE
import business.constants.Sort.LOWEST_PRICE
import business.constants.Sort.MOST_SALE
import business.constants.Sort.NEWEST
import business.constants.Sort.OLDEST
import business.core.UIComponentState
import presentation.ui.main.search.view_model.SearchEvent
import presentation.ui.main.search.view_model.SearchState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortDialog(
    state: SearchState,
    events: (SearchEvent) -> Unit,
) {

    val selectedSort = mutableStateOf(state.selectedSort)


    CustomAlertDialog(
        onDismissRequest = {
            events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
        },
        modifier = Modifier
            .fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background)
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
                events(SearchEvent.OnUpdateSelectedSort(NEWEST))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == NEWEST, onCheckedChange = {})
                Text("Newest", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(OLDEST))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == OLDEST, onCheckedChange = {})
                Text("Oldest", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(HIGHER_PRICE))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == HIGHER_PRICE, onCheckedChange = {})
                Text("Highest Price", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(LOWEST_PRICE))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == LOWEST_PRICE, onCheckedChange = {})
                Text("Lowest Price", style = MaterialTheme.typography.labelLarge)
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(MOST_SALE))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == MOST_SALE, onCheckedChange = {})
                Text("Most Sale", style = MaterialTheme.typography.labelLarge)
            }


        }

    }

}