plugins {
    id("foodrun.android.library")
    id("foodrun.android.hilt")
}

android {
    namespace = "com.rainyday.foodrun.core.data"
}

dependencies {
    implementation(project(":core:network"))
}