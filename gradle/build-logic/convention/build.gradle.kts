plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "foodrun.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "foodrun.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "foodrun.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidFeature") {
            id = "foodrun.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidRoom") {
            id = "foodrun.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidDatastore") {
            id = "foodrun.android.datastore"
            implementationClass = "AndroidDatastoreConventionPlugin"
        }
        register("androidNetwork") {
            id = "foodrun.android.network"
            implementationClass = "AndroidNetworkConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.plugins.android.application.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
    implementation(libs.plugins.android.library.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
    implementation(libs.plugins.kotlin.android.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
    implementation(libs.plugins.kotlin.compose.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
    implementation(libs.plugins.hilt.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
    implementation(libs.plugins.ksp.get().let {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    })
}