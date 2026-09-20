package com.razzaghi.shopingbykmp

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    // FIX: Removed the <*, *, *, *, *> type arguments entirely for AGP 9.x compatibility
    commonExtension: CommonExtension,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("android-compileSdk").get().toString().toInt()

        // Dynamic namespace derived from module path
        if (namespace == null) {
            val modulePath = path.replace(":", ".").removePrefix(".")
            namespace = "com.razzaghi.shopingbykmp.$modulePath"
        }

        // FIX: Using .apply {} to safely scope into the configuration blocks for AGP 9.x
        defaultConfig.apply {
            minSdk = libs.findVersion("android-minSdk").get().toString().toInt()
        }

        sourceSets.getByName("main").apply {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    configureKotlin()
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)

            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())

            freeCompilerArgs.addAll(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
}