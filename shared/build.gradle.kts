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

    task("testClasses")


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

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.property)
                implementation(libs.ktor.mock)
                implementation(libs.coroutines.test)
                implementation(libs.turbine.turbine)
                implementation(libs.mockk.io)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)

            }
        }
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
                implementation(libs.kotlinx.datetime)
                implementation(libs.androidx.datastore.preferences)
                // implementation(libs.image.loader)
                api(libs.koin.core)
                api(libs.koin.compose)
                api(libs.coil3)
                api(libs.coil3.network)
                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)


            }
        }

        androidMain {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                implementation(libs.ktor.okhttp)
                api(libs.coil3.gif)
                api(libs.coil3.svg)
                api(libs.coil3.core)
                api(libs.coil3.video)
                implementation(libs.system.ui.controller)
                implementation(libs.accompanist.permissions)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.ktor.darwin.ios)
                implementation(libs.ktor.ios)
            }
        }

    }
}


android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.razzaghi.shopingbykmp"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/composeResources")
    sourceSets["main"].res.srcDirs("src/commonMain/composeResources", "src/androidMain/res")

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
