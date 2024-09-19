plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )
}

android {
    namespace = "com.razzaghi.shopingbykmp.domain"
}