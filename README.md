# Shopping-By-KMP (Compose Multiplatform application)

Welcome to the documentation for the Jetpack Compose Multiplatform Shopping Application! 
This is a cross-platform application that is built using Jetpack Compose Multiplatform, a declarative framework for sharing UIs across multiple platforms with Kotlin. 
The application allows users to browse, search, and purchase products from a shopping catalog on Android, iOS, Desktop, Web, Automotive and AndroidTV.

![Wallpaper](screenshots/banner.png)
![Wallpaper](screenshots/banner2.png)


### Android & IOS
https://github.com/razaghimahdi/Shopping-By-KMP/assets/61207818/a4702cbf-1449-48b0-88f1-bcb9c42d273e


### Desktop
https://github.com/user-attachments/assets/2ceedcef-0ec8-4886-904a-3975e6c2ea6f


### AndroidTV
https://github.com/user-attachments/assets/9b7cbb10-3f95-4355-a9c7-3b23147dda18

### Web 
#### [Live](https://razaghimahdi.github.io/Shopping-By-KMP/)
https://github.com/user-attachments/assets/808a72b9-d591-44e4-a29f-16a1fd99e462




## Give a Star! ⭐
If you like or are using this project to learn or start your solution, please give it a star. Thanks!

## Related Articles📄
[How to handle error in best practice](https://medium.com/@razaghimahdi78/how-to-handle-errors-in-jetpack-compose-with-mvi-and-clean-architecture-55ab17b9c82d)

[How to set up Convention Gradle Plugin in CMP](https://medium.com/@razaghimahdi78/creating-convention-gradle-plugin-for-compose-multiplatform-1b4051e98f61)

[How to create custom Number Formatting in CMP](https://medium.com/@razaghimahdi78/number-formatting-in-jetpack-compose-multiplatform-b3fd01308f6c)

[How to upload in CMP Using Ktor](https://medium.com/@razaghimahdi78/uploading-in-compose-multiplatform-using-ktor-a-comprehensive-guide-f2dd28799a20)


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
| Category                    | ✔️ Implemented   |
| Edit Profile                | ✔️ Implemented   |
| Manage Address              | ✔️ Implemented   |
| Payment Method              | ✔️ Implemented   |
| My Orders                   | ✔️ Implemented   |
| My Coupons                  | ✔️ Implemented   |
| Notifications               | ✔️ Implemented   |
| Setting                     | ✔️ Implemented   |

## **Next Features 🏄‍♂️**
Obviously it would be more updates, What we are working on these days?
- **Product Comparison:** Easily compare features, prices, and reviews of two products side by side for a smarter shopping choice.
  
## Latest Features 🚀
### 🚗 Automotive Support
Added `AutomotiveApp` — the application is now compatible with **Android Automotive OS**, making it available for in-car systems.
### 🌐 Web Support
Introduced `WebApp` — access the app directly from your browser.
### 📺 Android TV Support
Added `TvApp` — enjoy the app on **Android TV** devices.
### 💻 Desktop Support
Introduced `DesktopApp` — the app now runs on desktop environments (Windows, macOS, Linux).
### ✨ Refined Splash Screen
Improved the splash screen UI with **smoother animations** for a better user experience.
### 🗺️ Google Maps Integration
Integrated `GoogleMap` for enhanced address input.  
Users can now **select their location on the map** before filling out address details, ensuring greater precision.



## Tech Stack 📚
- Kotlin Multiplatform 
- Kotlin Coroutines 
- Compose Multiplatform 
- Material3 
- Ktor 
- Datastore 
- Compose Navigation and Type safety
- Koin
- Coil3
- Kotest
- Fake Data
<!-- - Mockk -->
<!-- - Turbine -->

<!--
## Development 💻

Firs, let me say the backend is also in progress. You can find the backend source code [here](https://github.com/aydenGill/shop-admin-panel).

We appreciate any feedback or suggestions that you may have to help us improve the project.

We plan to add more features, improve the code quality, and make the application more user-friendly. 

Our goal is to create a high-quality, multiplatform shopping application that demonstrates the power and flexibility of Jetpack Compose.

Please stay tuned for updates and feel free to contribute to the project by submitting pull requests or opening issues. 
Together, we can create a great shopping application that meets the needs of users across multiple platforms.-->

## Challenges Faced 👨‍💻
During the development of this project, 
i encountered several challenges that required creative solutions. 
Some of the key challenges I addressed include:

- **Native Functionality Challenges:** Implementing native functionalities posed challenges in image selection, camera access permissions, location access permissions, google maps, status bar customization, and image upload via Ktor.
- **UI Testing:** Developing comprehensive UI tests across multiple platforms presented challenges in ensuring consistent behavior and visual presentation.
- **Unit Testing:** Writing unit tests for shared code and platform-specific implementations required careful consideration of differences in testing frameworks and environments.
- **User Session Management:** Securely managing user sessions across platforms involved implementing robust authentication mechanisms while prioritizing privacy and security.
- **UI/UX Design:** Crafting an intuitive and visually appealing UI/UX for diverse devices presented creative challenges, driving iterative design processes.

These challenges underscored the complexity of developing a cross-platform shopping application and pushed us to innovate and refine our solutions continually.


## Architecture 🏢
The Jetpack Compose Multiplatform Shopping Application is built using the Clean Architecture and the MVI (Model-View-Intent) pattern. 

## Testing 🧪
Testing is a crucial aspect of software development to ensure the reliability and functionality of the application. 
In the Shopping-By-KMP project, we utilize various testing frameworks and tools to maintain the quality of our codebase.

<!--
### Android 📱

When Android is one of your targets, 
you can get the same experience for Android as if you were developing an Android app using Jetpack Compose.

| Splash                                            | Sign In                                           | Sign Up                                           | Main(Home)                                        |
|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|---------------------------------------------------|
| <img src="screenshots/android1.png" width="300"/> | <img src="screenshots/android2.png" width="300"/> | <img src="screenshots/android3.png" width="300"/> | <img src="screenshots/android4.png" width="300"/> |
-->
<!--
### IOS 📱
> iOS support is in Alpha. It may change incompatibly and require manual migration in the future.

Compose Multiplatform shares most of its API with Jetpack Compose, the Android UI framework developed by Google. 
You can use the same APIs to build user interfaces for both Android and iOS.


| Splash                                        | Sign In                                       | Sign Up                                       | Main(Home)                                    |
|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|-----------------------------------------------|
| <img src="screenshots/ios1.png" width="300"/> | <img src="screenshots/ios2.png" width="300"/> | <img src="screenshots/ios3.png" width="300"/> | <img src="screenshots/ios4.png" width="300"/> |
-->

## Backend 🌐

This project features two powerful backend implementations: a **Kotlin-based backend using Ktor** for modern connected applications, and a **Laravel + Livewire** backend for managing an admin panel.

### Kotlin Backend (Primary)

> 🔗 [View Kotlin Backend Repository](https://github.com/razaghimahdi/shopping_by_ktor)

The Kotlin backend is built with **Ktor**, a modern framework for creating asynchronous web applications and services. It leverages **Kotlin Coroutines** for non-blocking execution and includes a set of carefully selected libraries to ensure security, scalability, and maintainability.

#### Tech Stack

- **Ktor** – Framework to build asynchronous connected applications using Kotlin Coroutines.
- **Exposed** – Lightweight SQL library with a typesafe DSL and DAO support for PostgreSQL.
- **PostgreSQL** – Open-source, advanced object-relational database.
- **Koin** – Pragmatic and lightweight dependency injection framework for Kotlin.
- **kotlinx-datetime** – Multiplatform date and time library.
- **Bcrypt** – Java-based implementation of the bcrypt password hashing algorithm.
- **Apache Commons Email** – Simplified email-sending library built on JavaMail.
- **ktor-openapi-tools** – Plugin to generate OpenAPI specs with Swagger UI and ReDoc.
- **Valiktor** – Type-safe, fluent DSL for validating Kotlin objects.

This stack enables building reactive APIs, secure user management, and robust database interactions, all with a clean and maintainable architecture.

---

### Laravel Admin Panel

> 🔗 [View Laravel Admin Panel Repository](https://github.com/soheilkhaledabdi/shop-admin-panel)

Alongside the Kotlin backend, the project also provides a **Laravel + Livewire** admin panel for managing the e-commerce platform.

#### Features

- **Admin Dashboard**: Manage products, categories, orders, and customers seamlessly.
- **Laravel & Livewire**: Combine Laravel’s backend power with Livewire’s reactive UI components.
- **Comprehensive API**: Designed for extensibility and easy integration with the shopping platform.



## How to run web locally ?
✅ For development:
```./gradlew :webApp:jsBrowserDevelopmentRun```

✅ For production:
```./gradlew :webApp:jsBrowserProductionRun```


## Contributing
Contributions are welcome! If you have any feedback or suggestions, please don't hesitate to let us know. 
We appreciate your contributions and support. Also if you find a bug or would like to create a new feature, please submit a pull request.

## License
This library is licensed under the MIT License. See [LICENSE.txt](https://github.com/razaghimahdi/Shoping-By-KMP/blob/master/LICENSE)


### more ideas:
https://github.com/JetBrains/compose-multiplatform-ios-android-template
https://github.com/JetBrains/compose-multiplatform

Developed by Mahdi Razzaghi Ghaleh
