package com.razzaghi.shopingbykmp.business.core

sealed class NetworkState{

   data object Good: NetworkState()

   data object Failed: NetworkState()

}
