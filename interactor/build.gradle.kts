plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core)
            implementation(projects.datasource)
            implementation(projects.domain)
            implementation(libs.bundles.ktor)
        }
    }
}

android {
    namespace = "com.razzaghi.shopingbykmp.interactor"
}