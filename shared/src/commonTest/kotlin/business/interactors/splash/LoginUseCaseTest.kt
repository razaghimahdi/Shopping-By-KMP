package business.interactors.splash

import business.core.AppDataStore
import business.core.DataState
import business.core.ProgressBarState
import business.core.UIComponent
import business.datasoruce.datastore.AppDataStoreFake
import business.datasoruce.network.splash.LoginServiceResponseType
import business.datasoruce.network.splash.LoginFakeDataGenerator
import business.datasoruce.network.splash.SplashServiceFake
import business.datasource.network.splash.SplashService
import io.kotest.common.runBlocking
import kotlinx.coroutines.flow.toList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var service: SplashService
    private lateinit var appDataStoreManager: AppDataStore
    private var email = ""
    private var password = ""


    @Test
    fun `Test if request time out`() = runBlocking {

        // setup
        service = SplashServiceFake.build(type = LoginServiceResponseType.TimeOut)
        appDataStoreManager = AppDataStoreFake()
        loginUseCase = LoginUseCase(service, appDataStoreManager)
        email = LoginFakeDataGenerator.email
        password = LoginFakeDataGenerator.password

        // Execute the use-case
        val result = loginUseCase.execute(
            LoginUseCase.Params(
                email = email,
                password = password
            )
        ).toList()


        // First emission should be loading
        assertEquals(result[0], DataState.Loading(ProgressBarState.ButtonLoading))

        // Confirm entered email is not empty
        assertTrue(email.isNotEmpty())

        // Confirm entered password is not empty
        assertTrue(password.isNotEmpty())

        // Confirm second emission is dialog
        assertTrue(result[1] is DataState.Response)
        assertEquals(result[1], DataState.Response(uiComponent = UIComponent.None("")))


        // Confirm loading state is IDLE
        assertEquals(result[2], DataState.Loading(ProgressBarState.Idle))

    }


    @Test
    fun `Test if email or password is empty`() = runBlocking {

        // setup
        service = SplashServiceFake.build(type = LoginServiceResponseType.FillDataCurrently)
        appDataStoreManager = AppDataStoreFake()
        loginUseCase = LoginUseCase(service, appDataStoreManager)

        // Execute the use-case
        val result = loginUseCase.execute(
            LoginUseCase.Params(
                email = email,
                password = password
            )
        ).toList()


        // First emission should be loading
        assertEquals(result[0], DataState.Loading(ProgressBarState.ButtonLoading))

        // Confirm second emission is dialog
        assertTrue(result[1] is DataState.Response)

        // Confirm third emission is data
        assertTrue(result[2] is DataState.Data)

        // Confirm entered email is empty
        assertTrue(email.isEmpty())

        // Confirm entered password is empty
        assertTrue(password.isEmpty())

        // Confirm loading state is IDLE
        assertEquals(result[3], DataState.Loading(ProgressBarState.Idle))

    }

    @Test
    fun `Test if result is null`() = runBlocking {

        // setup
        service = SplashServiceFake.build(type = LoginServiceResponseType.MalformedData)
        appDataStoreManager = AppDataStoreFake()
        loginUseCase = LoginUseCase(service, appDataStoreManager)
        email = LoginFakeDataGenerator.email
        password = LoginFakeDataGenerator.password

        // Execute the use-case
        val result = loginUseCase.execute(
            LoginUseCase.Params(
                email = email,
                password = password
            )
        ).toList()


        // First emission should be loading
        assertEquals(result[0], DataState.Loading(ProgressBarState.ButtonLoading))


        // Confirm entered email is not empty
        assertTrue(email.isNotEmpty())

        // Confirm entered password is not empty
        assertTrue(password.isNotEmpty())

        // Confirm second emission is dialog
        assertTrue(result[1] is DataState.Response)

        // Confirm third emission is data
        assertTrue(result[2] is DataState.Data)

        // Confirm result if null
        assertEquals(result[2], DataState.Data<String>(null, false))

        // Confirm loading state is IDLE
        assertEquals(result[3], DataState.Loading(ProgressBarState.Idle))

    }

    @Test
    fun `Test login success`() = runBlocking {

        // setup
        service = SplashServiceFake.build(type = LoginServiceResponseType.GoodData)
        appDataStoreManager = AppDataStoreFake()
        loginUseCase = LoginUseCase(service, appDataStoreManager)
        email = LoginFakeDataGenerator.email
        password = LoginFakeDataGenerator.password

        // Execute the use-case
        val result = loginUseCase.execute(
            LoginUseCase.Params(
                email = email,
                password = password
            )
        ).toList()


        // First emission should be loading
        assertEquals(result[0], DataState.Loading(ProgressBarState.ButtonLoading))


        // Confirm entered email is not empty
        assertTrue(email.isNotEmpty())

        // Confirm entered password is not empty
        assertTrue(password.isNotEmpty())

        // Confirm second emission is dialog
        assertTrue(result[1] is DataState.Response)

        // Confirm third emission is data
        assertTrue(result[2] is DataState.Data)


        // Confirm loading state is IDLE
        assertEquals(result[3], DataState.Loading(ProgressBarState.Idle))


    }


}