plugins {
    id("foodrun.android.feature")
}

android {
    namespace = "com.rainyday.foodrun.feature.auth"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:ui"))
}