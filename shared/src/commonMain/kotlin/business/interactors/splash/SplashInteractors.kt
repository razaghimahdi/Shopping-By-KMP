package business.interactors.splash

import business.core.AppDataStore
import business.datasource.network.splash.SplashService

data class SplashInteractors(
    val loginInteractor: LoginInteractor,
    val registerInteractor: RegisterInteractor,
    val checkTokenInteractor: CheckTokenInteractor,
) {

    companion object Factory {
        fun build(appDataStoreManager: AppDataStore): SplashInteractors {
            val service = SplashService.Factory.build()
            return SplashInteractors(
                checkTokenInteractor = CheckTokenInteractor(appDataStoreManager),
                loginInteractor = LoginInteractor(service, appDataStoreManager),
                registerInteractor = RegisterInteractor(service, appDataStoreManager),
            )
        }
    }
}