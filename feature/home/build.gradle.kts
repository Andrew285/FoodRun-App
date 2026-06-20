plugins {
    id("foodrun.android.feature")
}

android {
    namespace = "com.rainyday.foodrun.feature.home"
}

dependencies {
    implementation(project(":core:data"))
}