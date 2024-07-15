import com.razzaghi.shopingbykmp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.razzaghi.shopingbykmp.configureKotlinAndroid
import com.android.build.api.dsl.LibraryExtension


class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        with(pluginManager) {
            apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("androidApplication").get().get().pluginId)
            apply(libs.findPlugin("composeMultiplatform").get().get().pluginId)
            apply(libs.findPlugin("compose.compiler").get().get().pluginId)
            apply(libs.findPlugin("kotlin.serialization").get().get().pluginId)
            apply(libs.findPlugin("kotlin.parcelize").get().get().pluginId)
        }


    }
}