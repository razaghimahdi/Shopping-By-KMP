package business.core

sealed class NetworkState{

    object Good: NetworkState()

    object Failed: NetworkState()

}
