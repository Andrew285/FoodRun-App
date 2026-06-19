import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("foodrun.android.library.compose")
                apply("foodrun.android.hilt")
            }
            dependencies {
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("navigation.compose").get())
                add("implementation", libs.findLibrary("lifecycle.viewmodel.compose").get())
                add("implementation", libs.findLibrary("coroutines.android").get())
            }
        }
    }
}