pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FoodRun"
include(":app")
include(":core:data")
include(":core:network")
include(":core:domain")
include(":core:ui")
include(":core:database")
include(":core:datastore")
include(":feature:auth")
include(":feature:home")
include(":feature:restaurant")
include(":feature:cart")
include(":feature:order")
