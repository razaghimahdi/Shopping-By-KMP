package business.interactors.main

import business.core.AppDataStore
import business.datasource.network.main.MainService

data class MainInteractors(
    val homeInteractor: HomeInteractor,
    val productInteractor: ProductInteractor,
) {

    companion object Factory {
        fun build(appDataStoreManager: AppDataStore): MainInteractors {
            val service = MainService.Factory.build()
            return MainInteractors(
                homeInteractor = HomeInteractor(service, appDataStoreManager),
                productInteractor = ProductInteractor(service, appDataStoreManager),
            )
        }
    }
}