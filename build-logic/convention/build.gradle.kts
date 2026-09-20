plugins {
    `kotlin-dsl`
}

group = "com.razzaghi.shopingbykmp.buildlogic"

dependencies {
    // Changed from compileOnly to implementation so they are available at runtime!
    implementation(libs.plugins.androidApplication.toDep())
    implementation(libs.plugins.androidLibrary.toDep())
    implementation(libs.plugins.composeMultiplatform.toDep())
    implementation(libs.plugins.composeCompiler.toDep())
    implementation(libs.plugins.kotlinMultiplatform.toDep())
    implementation(libs.plugins.kotlinSerialization.toDep())
}

fun Provider<PluginDependency>.toDep() = map {
    "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "com.razzaghi.shopingbykmp.kotlinMultiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("core") {
            id = "com.razzaghi.shopingbykmp.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("androidApp") {
            id = "com.razzaghi.shopingbykmp.androidApp"
            implementationClass = "AndroidAppConventionPlugin"
        }
    }
}