package business.datasoruce.network.splash

sealed class LoginServiceResponseType {

   data object Empty : LoginServiceResponseType()

   data object MalformedData : LoginServiceResponseType()

   data object FillDataCurrently : LoginServiceResponseType()

   data object GoodData : LoginServiceResponseType()

   data object TimeOut : LoginServiceResponseType()


}
