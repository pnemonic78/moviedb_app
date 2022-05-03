plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization") version "1.6.10"
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    // Jetpack
    implementation(Android.Dependency.core)

    // JSON
    implementation(Kotlin.Dependency.json)

    // Test
    testImplementation(Android.Dependency.junit)
    androidTestImplementation(Android.Dependency.junit_ext)
    androidTestImplementation(Android.Dependency.espresso_core)
}