import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.shopping.androidApp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            // This block tells Kotlin which source set is for testing,
            // but the dependencies themselves don't go here.
            sourceSetTree.set(KotlinSourceSetTree.test)
        }
    }
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                // implementation("androidx.compose.material:material-ripple:1.7.0-alpha05")
            }
        }
        // ✅ Add this block for your instrumented test dependencies
        val androidInstrumentedTest by getting {
            dependencies {
               // implementation("androidx.compose.ui:ui-test-junit4-android:1.6.4")
               // debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.4")
            }
        }
    }
}


android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    namespace = "com.razzaghi.shopingbykmp.android"
    defaultConfig {
        applicationId = "com.razzaghi.shopingbykmp.android"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.android.version.code.get().toInt()
        versionName = libs.versions.android.version.name.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

