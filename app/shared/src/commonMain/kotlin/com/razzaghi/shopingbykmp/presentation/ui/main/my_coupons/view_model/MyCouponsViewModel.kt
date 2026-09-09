package presentation.ui.main.my_coupons.view_model

import business.core.BaseViewModel
import business.core.NetworkState
import business.domain.main.Coupons

class MyCouponsViewModel : BaseViewModel<MyCouponsEvent, MyCouponsState, Nothing>() {

    override fun setInitialState() = MyCouponsState()


    override fun onTriggerEvent(event: MyCouponsEvent) {
        when (event) {

            is MyCouponsEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is MyCouponsEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        state.value = state.value.copy(
            couponsList = listOf(
                Coupons(
                    title = "WELCOME24",
                    desc = "Add items worth $2 more to unlock",
                    code = "SDSD23SD",
                    offPercent = 50
                ),
                Coupons(
                    title = "BLACKFRIDAY24",
                    desc = "Add items worth $1 more to unlock",
                    code = "S2323SD",
                    offPercent = 75
                ),
                Coupons(
                    title = "HOLYDAY24",
                    desc = "Add items worth $15 more to unlock",
                    code = "SFER23SD",
                    offPercent = 15
                ),
                Coupons(
                    title = "WELCOME24",
                    desc = "Add items worth $2 more to unlock",
                    code = "SDSD23SD",
                    offPercent = 50
                ),
                Coupons(
                    title = "BLACKFRIDAY24",
                    desc = "Add items worth $1 more to unlock",
                    code = "S2323SD",
                    offPercent = 75
                ),
                Coupons(
                    title = "HOLYDAY24",
                    desc = "Add items worth $15 more to unlock",
                    code = "SFER23SD",
                    offPercent = 15
                ),
            )
        )
    }

    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        setState { copy(networkState = networkState) }
    }


}