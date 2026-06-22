plugins {
    id("foodrun.android.feature")
}

android {
    namespace = "com.rainyday.foodrun.feature.order"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}