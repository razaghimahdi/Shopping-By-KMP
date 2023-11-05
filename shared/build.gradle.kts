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
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api(compose.materialIconsExtended)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.negotiation)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation(libs.image.loader)
                implementation(libs.precompose)
                implementation(libs.precompose.viewmodel)
            }
        }

        androidMain {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                implementation(libs.ktor.okhttp)
                /*  api("androidx.activity:activity-compose:1.8.0")
                  api("androidx.appcompat:appcompat:1.6.1")
                  api("androidx.core:core-ktx:1.12.0")
                  implementation("io.ktor:ktor-client-okhttp:2.3.5")*/
            }
        }
        iosMain {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.5")
                implementation("io.ktor:ktor-client-ios:2.3.5")
                /*
                implementation(libs.ktor.darwin)
                implementation(libs.ktor.ios) */
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
