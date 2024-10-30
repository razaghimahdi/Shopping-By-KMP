plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
    alias(libs.plugins.kotlinCocoapods)
}


kotlin {

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods{
        version = "1.0"
        summary = "Shopping By KMP"
        name = "MyCocoaPod"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }

      //  pod("lottie-ios")
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


