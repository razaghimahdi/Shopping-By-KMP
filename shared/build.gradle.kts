plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
    alias(libs.plugins.ktlint)
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

    }
}


