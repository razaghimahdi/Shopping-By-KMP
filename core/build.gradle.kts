plugins {
    id("com.razzaghi.shopingbykmp.core")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Dependency Injection
            api(libs.koin.core)

            // Ktor Networking
            api(libs.ktor.core)
            api(libs.ktor.logging)
            api(libs.ktor.serialization)
            api(libs.ktor.negotiation)

            // Persistence
            api(libs.androidx.datastore.core)
            api(libs.androidx.datastore.preferences.core)
        }

        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
        }

        getByName("desktopMain").dependencies {
            implementation(libs.ktor.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin.ios)
            implementation(libs.ktor.ios)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
            implementation(libs.ktor.client.json)
        }
    }
}