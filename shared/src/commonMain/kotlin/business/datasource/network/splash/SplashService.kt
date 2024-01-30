package business.datasource.network.splash

import business.datasource.network.common.MainGenericResponse

interface SplashService {
    companion object {
        const val REGISTER = "register"
        const val LOGIN = "login"
    }

    suspend fun login(email: String, password: String): MainGenericResponse<String?>

    suspend fun register(name: String, email: String, password: String): MainGenericResponse<String?>




}