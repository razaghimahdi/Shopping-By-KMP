package business.interactors.main

//import business.core.AppDataStore
//import business.core.DataState
//import business.datasoruce.datastore.AppDataStoreFake
//import business.datasoruce.network.main.MainServiceFake
//import business.datasoruce.network.main.MainServiceResponseType
//import business.datasource.network.main.MainService
//import io.kotest.common.runBlocking
//import kotlinx.coroutines.flow.toList
//import kotlin.test.Test
//import kotlin.test.assertTrue
//
//class HomeInteractorTest {
//
//
//    private lateinit var homeInteractor: HomeInteractor
//    private lateinit var service: MainService
//    private lateinit var appDataStoreManager: AppDataStore
//
//
//    @Test
//    fun `Test if retrieving data from api is success`() = runBlocking {
//
//        // setup
//        service = MainServiceFake.build(type = MainServiceResponseType.GoodData)
//        appDataStoreManager = AppDataStoreFake()
//        homeInteractor = HomeInteractor(service, appDataStoreManager)
//        appDataStoreManager.setValue("key", "value")
//
//        // Execute the use-case
//        val result = homeInteractor.execute().toList()
//
//        // First emission should be loading
//        assertTrue(result[0] is DataState.Loading)
//
//        // Confirm token is not empty
//        assertTrue(!appDataStoreManager.readValue("token").isNullOrEmpty())
//
//
//
//
//
//    }
//
//
//}