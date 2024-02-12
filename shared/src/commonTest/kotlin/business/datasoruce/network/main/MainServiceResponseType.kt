package business.datasoruce.network.main

sealed class MainServiceResponseType {

   data object Empty : MainServiceResponseType()

   data object UnauthenticatedData : MainServiceResponseType()

   data object MalformedData : MainServiceResponseType()

   data object GoodData : MainServiceResponseType()

   data object TimeOut : MainServiceResponseType()


}
