import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlin.serialization)
}

ktlint {
    android = true
    outputToConsole = true
    outputColorName = "RED"
}

kotlin {

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

    jvm()

    js(IR) {
        browser()
    }

    sourceSets {

        commonTest {
            dependencies {

            }
        }
        commonMain {
            dependencies {

            }
        }

        androidMain {
            dependencies {


            }
        }
        iosMain {
            dependencies {

            }
        }
        jvmMain {
            dependencies {

            }
        }

        jsMain {
            dependencies{

            }
        }

    }
}


