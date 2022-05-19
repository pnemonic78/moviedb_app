plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization") version Kotlin.Version.kotlin
}

android {
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        minSdk = Android.Version.minSdk
        targetSdk = Android.Version.targetSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(Android.Jetpack.material)

    implementation(Android.JSON.kotlin)

    implementation(Android.Logging.timber)

    testImplementation(Android.Test.junit)
    androidTestImplementation(Android.Test.junit_ext)
    androidTestImplementation(Android.Test.espresso_core)
}