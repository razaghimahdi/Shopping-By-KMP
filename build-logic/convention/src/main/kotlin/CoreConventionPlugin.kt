import com.android.build.api.dsl.LibraryExtension
import com.razzaghi.shopingbykmp.configureKotlinAndroid
import com.razzaghi.shopingbykmp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CoreConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("androidLibrary").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeCompiler").get().get().pluginId)
            apply(libs.findPlugin("kotlinSerialization").get().get().pluginId)
        }

        val composeDeps = extensions.getByType<ComposeExtension>().dependencies

        extensions.configure<LibraryExtension>(::configureKotlinAndroid)

        extensions.configure<KotlinMultiplatformExtension> {
            androidTarget()
            jvm("desktop")
            iosX64()
            iosArm64()
            iosSimulatorArm64()
            js { browser() }

            applyDefaultHierarchyTemplate()

            sourceSets.apply {
                commonTest.dependencies {
                    implementation(kotlin("test"))
                    implementation(libs.findLibrary("kotlin-test").get())
                }

                commonMain.dependencies {
                    implementation(composeDeps.runtime)
                    implementation(composeDeps.foundation)
                    implementation(composeDeps.animation)
                    implementation(composeDeps.material3)
                    implementation(composeDeps.components.resources)
                    implementation(composeDeps.components.uiToolingPreview)

                    // Notice the exact hyphens matching your TOML file here
                    implementation(libs.findLibrary("kotlinx-serialization-json").get())
                    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                    implementation(libs.findLibrary("kotlinx-datetime").get())
                    implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
                    implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
                }

                androidMain.dependencies {
                    implementation(libs.findLibrary("androidx-appcompat").get())
                    implementation(libs.findLibrary("androidx-core-ktx").get())
                }

                getByName("desktopMain").dependencies {
                    implementation(composeDeps.desktop.currentOs)
                    implementation(libs.findLibrary("kotlinx-coroutinesSwing").get())
                }
            }
        }
    }
}