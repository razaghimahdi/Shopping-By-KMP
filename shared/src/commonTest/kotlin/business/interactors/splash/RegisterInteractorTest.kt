package business.interactors.splash

/*class RegisterInteractorTest {

    private lateinit var registerInteractor: RegisterInteractor
    private lateinit var service: SplashService
    private lateinit var appDataStoreManager: AppDataStore
    private var email = ""
    private var password = ""
    private var name = ""



    @Test
    fun `Test register success`() = runBlocking {

        // setup
        service = mockk(relaxed = true)
        appDataStoreManager = mockk(relaxed = true)
        registerInteractor = RegisterInteractor(service, appDataStoreManager)
        email = LoginFakeDataGenerator.email
        password = LoginFakeDataGenerator.password


        // Execute the use-case
        val result = registerInteractor.execute(
            email = email,
            password = password,
            name = name
        ).toList()



        // First emission should be loading
        assertEquals(result[0], DataState.Loading<String>(ProgressBarState.ButtonLoading))


        // Confirm entered email is not empty
        assertTrue(email.isNotEmpty())

        // Confirm entered password is not empty
        assertTrue(password.isNotEmpty())

        // Confirm second emission is dialog
        assertTrue(result[1] is DataState.Response)

        // Confirm third emission is data
        assertTrue(result[2] is DataState.Data)


        // Confirm loading state is IDLE
        assertEquals(result[3], DataState.Loading<String>(ProgressBarState.Idle))



    }


}*/