package presentation.ui.main.home

import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import business.core.UIComponent
import business.datasoruce.network.main.HomeFakeDataGenerator
import business.datasoruce.serializeHomeData
import kotlinx.coroutines.flow.MutableSharedFlow
import presentation.ui.main.home.view_model.HomeState
import kotlin.test.Test

class HomeScreenTest {


    private val homeData = serializeHomeData(HomeFakeDataGenerator.goodData)


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `image slider and categories and flash sale and most sale and newest products and selected location are shown`() =
        runComposeUiTest {
            setContent {
                val state = remember {
                    HomeState(home = homeData)
                }
                val errors = MutableSharedFlow<UIComponent>()
                HomeScreen(state = state, errors = errors)
            }
            //onRoot().printToLog("UI Hierarchy")


            onNodeWithText("Computer").assertIsDisplayed()
            onAllNodesWithText("Nike model-934")[0].assertIsDisplayed()
        }


}