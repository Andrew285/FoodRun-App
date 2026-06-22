plugins {
    id("foodrun.android.feature")
    id("foodrun.android.room")
}

android {
    namespace = "com.rainyday.foodrun.feature.cart"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
}