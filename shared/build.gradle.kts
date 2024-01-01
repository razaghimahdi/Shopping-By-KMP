plugins {
    /*   alias(libs.plugins.kotlinMultiplatform)
       alias(libs.plugins.androidLibrary)
   */
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    iosArm64()
    iosSimulatorArm64()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.animation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(compose.materialIconsExtended)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.negotiation)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.androidx.datastore.preferences)
               // implementation(libs.image.loader)
                api(libs.koin.core)
                api(libs.koin.compose)
                api(libs.coil3)
                api(libs.coil3.network)
                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)
/*
                api(libs.mvvm.core)
                api(libs.mvvm.compose)
                api(libs.mvvm.flow)
                api(libs.mvvm.flow.compose)*/

            }
        }

        androidMain {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                implementation(libs.ktor.okhttp)
            }
        }
        iosMain {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.5")
                implementation("io.ktor:ktor-client-ios:2.3.5")
            }
        }

    }
}


android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.razzaghi.shopingbykmp"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
