# Shoping-By-KMP (Compose Multiplatform mobile application)

Welcome to the documentation for the Jetpack Compose Multiplatform Shopping Application! 
This is a cross-platform application that is built using Jetpack Compose Multiplatform, a declarative framework for sharing UIs across multiple platforms with Kotlin. 
The application allows users to browse, search, and purchase products from a shopping catalog on Android, iOS.



https://github.com/razaghimahdi/Shoping-By-KMP/assets/61207818/5d094b0e-a28d-43a9-9ea8-d4358dd00d36



## Give a Star! ⭐
If you like or are using this project to learn or start your solution, please give it a star. Thanks!

## Development

Firs, let me say the backend development is also in progress. You can find the backend source code [here](https://github.com/soheilkhaledabdi/shop).

This project is a work in progress and is not yet complete. 
We will continue to work on it and update it regularly until we have a complete shopping application. 
We appreciate any feedback or suggestions that you may have to help us improve the project.

We plan to add more features, improve the code quality, and make the application more user-friendly. 
Our goal is to create a high-quality, multiplatform shopping application that demonstrates the power and flexibility of Jetpack Compose.

Please stay tuned for updates and feel free to contribute to the project by submitting pull requests or opening issues. 
Together, we can create a great shopping application that meets the needs of users across multiple platforms.

![mobile-app-development-banner](https://user-images.githubusercontent.com/61207818/232203047-54940b08-d53f-41ce-a313-483a5fbeb9d3.jpg)

## Architecture
The Jetpack Compose Multiplatform Shopping Application is built using the Clean Architecture and the MVI (Model-View-Intent) pattern. 
The application is divided into the following layers:

**Presentation**: This layer includes the Jetpack Compose user interface components and logic. 
It's also responsible for mapping data from the domain layer into a format that can be displayed by the user interface.

**Domain**: This layer includes the business logic and use cases of the application. It's also responsible for defining the data models and the repository interfaces.

**Data**: This layer includes the repository implementation that fetches data from the backend using Ktor.

 <img src="https://user-images.githubusercontent.com/61207818/232203143-1815f502-18d4-4051-b636-dc016699c770.png" alt="Clean Architecture in Android" width="600"/>


### Android

When Android is one of your targets, 
you can get the same experience for Android as if you were developing an Android app using Jetpack Compose.

| Splash                                            | Sign In                                           | Sign Up                                           | Main(Home)                                        |
|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|
| <img src="screenshots/android1.png" width="300"/> | <img src="screenshots/android2.png" width="300"/> | <img src="screenshots/android3.png" width="300"/> | <img src="screenshots/android4.png" width="300"/> |


### IOS
> iOS support is in Alpha. It may change incompatibly and require manual migration in the future.

Compose Multiplatform shares most of its API with Jetpack Compose, the Android UI framework developed by Google. 
You can use the same APIs to build user interfaces for both Android and iOS.


| Splash                                        | Sign In                                       | Sign Up                                       | Main(Home)                                    |
|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|
| <img src="screenshots/ios1.png" width="300"/> | <img src="screenshots/ios2.png" width="300"/> | <img src="screenshots/ios3.png" width="300"/> | <img src="screenshots/ios4.png" width="300"/> |

### Backend
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
