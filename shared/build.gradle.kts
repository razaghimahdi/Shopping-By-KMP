plugins {
    alias(libs.plugins.shopping.kotlinMultiplatform)
    alias(libs.plugins.shopping.shared)
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
                implementation(projects.core)
                implementation(projects.domain)
                implementation(projects.datasource)
                implementation(projects.interactor)
            }
        }
        commonMain {
            dependencies {
                implementation(projects.core)
                implementation(projects.domain)
                implementation(projects.datasource)
                implementation(projects.interactor)
            }
        }
        androidMain {
            dependencies {
                implementation(projects.core)
                implementation(projects.domain)
                implementation(projects.datasource)
                implementation(projects.interactor)
            }
        }
        iosMain {
            dependencies {
                implementation(projects.core)
                implementation(projects.domain)
                implementation(projects.datasource)
                implementation(projects.interactor)
            }
        }
    }
}
