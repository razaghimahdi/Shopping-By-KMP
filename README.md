# Shopping-By-KMP (Compose Multiplatform application)

Welcome to the documentation for the Jetpack Compose Multiplatform Shopping Application! 
This is a cross-platform application that is built using Jetpack Compose Multiplatform, a declarative framework for sharing UIs across multiple platforms with Kotlin. 
The application allows users to browse, search, and purchase products from a shopping catalog on Android, iOS.






https://github.com/razaghimahdi/Shopping-By-KMP/assets/61207818/a4702cbf-1449-48b0-88f1-bcb9c42d273e






## Give a Star! ⭐
If you like or are using this project to learn or start your solution, please give it a star. Thanks!

## Features ✨
| Feature                     | Status           |
|-----------------------------|------------------|
| Login, Sign Up              | ✔️ Implemented   |
| Home                        | ✔️ Implemented   |
| Product Detail and Comments | ✔️ Implemented   |
| Wishlist                    | ✔️ Implemented   |
| Cart                        | ✔️ Implemented   |
| Profile                     | ✔️ Implemented   |
| Search                      | ✔️ Implemented   |
| Category                    | ⚠️ Working on it |

## Tech Stack 📚
- Kotlin Multiplatform 
- Kotlin Coroutines 
- Compose Multiplatform 
- Material3 
- Ktor 
- Datastore 
- Precompose 
- Koin
- Coil
- Turbine
- Kotest
- Mockk

## Development 💻

Firs, let me say the backend is also in progress. You can find the backend source code [here](https://github.com/soheilkhaledabdi/shop).

This project is a work in progress and is not yet complete. 
We will continue to work on it and update it regularly until we have a complete shopping application. 
We appreciate any feedback or suggestions that you may have to help us improve the project.

We plan to add more features, improve the code quality, and make the application more user-friendly. 
Our goal is to create a high-quality, multiplatform shopping application that demonstrates the power and flexibility of Jetpack Compose.

Please stay tuned for updates and feel free to contribute to the project by submitting pull requests or opening issues. 
Together, we can create a great shopping application that meets the needs of users across multiple platforms.

![mobile-app-development-banner](https://user-images.githubusercontent.com/61207818/232203047-54940b08-d53f-41ce-a313-483a5fbeb9d3.jpg)

## Architecture 🏢
The Jetpack Compose Multiplatform Shopping Application is built using the Clean Architecture and the MVI (Model-View-Intent) pattern. 
The application is divided into the following layers:

**Presentation**: This layer includes the Jetpack Compose user interface components and logic. 
It's also responsible for mapping data from the domain layer into a format that can be displayed by the user interface.

**Domain**: This layer includes the business logic and use cases of the application. It's also responsible for defining the data models and the repository interfaces.

**Data**: This layer includes the repository implementation that fetches data from the backend using Ktor.

 <img src="https://user-images.githubusercontent.com/61207818/232203143-1815f502-18d4-4051-b636-dc016699c770.png" alt="Clean Architecture in Android" width="600"/>


## Testing 🧪

### Overview
Testing is a crucial aspect of software development to ensure the reliability and functionality of the application. 
In the Shoping-By-KMP project, we utilize various testing frameworks and tools to maintain the quality of our codebase.

### Testing Frameworks
#### Kotest
Kotest is a flexible and comprehensive testing framework for Kotlin. 
It supports various styles of testing, including property testing, behavior-driven development (BDD), and more. 
In our project, Kotest is used to write unit tests for different components.

#### Turbine
Turbine is a testing library designed for Kotlin Flows. 
It simplifies the testing of asynchronous code, making it easier to write concise and readable tests for reactive programming. 
Turbine is utilized in our project to test code that involves Kotlin Flows.

#### Mockk
Mockk is a mocking library for Kotlin. 
It enables the creation of mock objects for testing purposes, allowing us to isolate and test individual components in isolation. 
Mockk is used in conjunction with other testing frameworks to create mock objects and verify interactions in our unit tests.

#### Fake Data
Fake data generation is a crucial aspect of testing, 
especially for scenarios where real data may not be suitable or readily available. 
In our testing suite, we make use of fake data to simulate various scenarios and ensure that our application behaves as expected under different conditions.



### Android 📱

When Android is one of your targets, 
you can get the same experience for Android as if you were developing an Android app using Jetpack Compose.
<!--
| Splash                                            | Sign In                                           | Sign Up                                           | Main(Home)                                        |
|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|
| <img src="screenshots/android1.png" width="300"/> | <img src="screenshots/android2.png" width="300"/> | <img src="screenshots/android3.png" width="300"/> | <img src="screenshots/android4.png" width="300"/> |
-->

### IOS 📱
> iOS support is in Alpha. It may change incompatibly and require manual migration in the future.

Compose Multiplatform shares most of its API with Jetpack Compose, the Android UI framework developed by Google. 
You can use the same APIs to build user interfaces for both Android and iOS.

<!--
| Splash                                        | Sign In                                       | Sign Up                                       | Main(Home)                                    |
|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|
| <img src="screenshots/ios1.png" width="300"/> | <img src="screenshots/ios2.png" width="300"/> | <img src="screenshots/ios3.png" width="300"/> | <img src="screenshots/ios4.png" width="300"/> |
-->

### Backend 🌐
[This project](https://github.com/soheilkhaledabdi/shop) involves building a robust admin panel for managing an e-commerce platform. 
It utilizes **Laravel** for the backend structure and **Livewire** for dynamic frontend interactions. 
The panel comes with a comprehensive **API**, ensuring scalability and flexibility.

### Features
- **Admin Dashboard:** Manage products, categories, orders, and customers seamlessly.
- **Laravel & Livewire:** Leverage the power of Laravel's backend with Livewire for reactive UI.
- **Full API:** Enables external integrations and interactions with the shopping platform.

## Contributing
Contributions are welcome! If you have any feedback or suggestions, please don't hesitate to let us know. 
We appreciate your contributions and support. Also if you find a bug or would like to create a new feature, please submit a pull request.

## License
This library is licensed under the MIT License. See [LICENSE.txt](https://github.com/razaghimahdi/Shoping-By-KMP/blob/master/LICENSE)


### more ideas:
https://github.com/JetBrains/compose-multiplatform-ios-android-template

https://github.com/JetBrains/compose-multiplatform

Developed by Mahdi Razzaghi Ghaleh
