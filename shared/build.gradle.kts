import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
}

composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
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
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.okhttp)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }

    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg)
            packageName = "com.razzaghi.shopingbykmp"
            packageVersion = "1.0.0"
        }
    }
}