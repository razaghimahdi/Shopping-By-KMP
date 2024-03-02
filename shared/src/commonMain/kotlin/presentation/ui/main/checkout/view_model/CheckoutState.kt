package presentation.ui.main.checkout.view_model

import business.core.NetworkState
import business.core.ProgressBarState
import business.core.Queue
import business.core.UIComponent
import business.core.UIComponentState
import business.domain.main.Address
import business.domain.main.ShippingType

data class CheckoutState(
    val buyingSuccess: Boolean = false,
    val totalCost: Int = 0,
    val totalBasket: Int = 0,
    val addresses: List<Address> = listOf(),
    val selectedAddress: Address = Address(),
    val selectedShipping: ShippingType = shippingType_global.first(),
    val selectShippingDialogState: UIComponentState = UIComponentState.Hide,
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
