import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    // 1. iOS Targets
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    // 2. Desktop Target
    jvm("desktop")

    // 3. Web Target
    js {
        browser()
    }

    // 4. Android Target
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    sourceSets {
        commonMain.dependencies {
            // LINK TO YOUR BUSINESS LOGIC MODULE
            api(project(":core"))

            // Data & Architecture
            implementation(libs.kotlinx.datetime)

            // Compose Core
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            // Architecture & Navigation
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.compose.navigation)

            // Third-Party UI (This fixes the missing 'io' references!)
            implementation(libs.coil3)
            implementation(libs.coil3.network)
            implementation(libs.koin.compose)

            // Moko / PreCompose
            implementation(libs.precompose)
            implementation(libs.precompose.viewmodel)
            implementation(libs.mvvm.compose)
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)

            // Android UI specifics
            implementation(libs.system.ui.controller)
            implementation(libs.accompanist.permissions)
            implementation(libs.maps.compose)
            implementation(libs.play.services.maps)
            implementation(libs.play.services.location)

            // Coil specific formats
            implementation(libs.coil3.video)
            implementation(libs.coil3.gif)
            implementation(libs.coil3.svg)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
}

android {
    namespace = "com.razzaghi.shopingbykmp.app.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}