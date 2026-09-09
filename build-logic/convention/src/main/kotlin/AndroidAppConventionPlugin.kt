import com.android.build.api.dsl.ApplicationExtension
import com.razzaghi.shopingbykmp.configureKotlinAndroid
import com.razzaghi.shopingbykmp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("androidApplication").get().get().pluginId)
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("composeCompiler").get().get().pluginId)
            apply(libs.findPlugin("kotlinSerialization").get().get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig {
                targetSdk = libs.findVersion("android-targetSdk").get().toString().toInt()
            }
        }
    }
}