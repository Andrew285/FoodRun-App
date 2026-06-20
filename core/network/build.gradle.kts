plugins {
    id("foodrun.android.library")
    id("foodrun.android.hilt")
    id("foodrun.android.network")
}

android {
    namespace = "com.rainyday.foodrun.core.network"
}

dependencies {
    implementation(project(":core:datastore"))
}