package business.datasoruce.network

sealed class LoginServiceResponseType {

    object Empty : LoginServiceResponseType()

    object MalformedData : LoginServiceResponseType()

    object FillDataCurrently : LoginServiceResponseType()

    object GoodData : LoginServiceResponseType()

    object TimeOut : LoginServiceResponseType()


}
