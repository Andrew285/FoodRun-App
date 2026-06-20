plugins {
    id("foodrun.android.feature")
}

android {
    namespace = "com.rainyday.foodrun.feature.restaurant"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}