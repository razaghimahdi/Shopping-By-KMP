package business.util

import business.core.DataState
import business.core.UIComponent
import business.datasource.network.common.JAlertResponse


fun <T> handleUseCaseException(e: Exception): DataState<T> {

    val splitList: List<String> = e.message?.split(THROWABLE_DIVIDER) ?: listOf()


    if (splitList.size<=1) {
      /*  return  DataState.Response<T>(
            uiComponent = UIComponent.Dialog(
                alert = JAlertResponse(FAILED_NETWORK_TITLE, FAILED_NETWORK)
            )
        )*/
        return DataState.Response(
            uiComponent = UIComponent.None("")
        )
    }


    val title = splitList[1]
    val message = splitList[2]

    e.printStackTrace()


    return DataState.Response(
        uiComponent = UIComponent.Dialog(
            JAlertResponse(
                title = title,
                message = message,
            )
        )
    )
}
