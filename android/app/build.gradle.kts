plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version Kotlin.Version.kotlin
}

android {
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        applicationId = "com.tikalk.tmdb.app"
        minSdk = Android.Version.minSdk
        targetSdk = Android.Version.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Android.Version.compose
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":ui-common"))
    implementation(project(":feature-now-playing"))

    implementation(Android.Inject.hilt)
    kapt(Android.Inject.hiltCompiler)

    implementation(Android.JSON.kotlin)

    implementation(Android.Logging.timber)

    implementation(Android.Network.logging)
    implementation(Android.Network.okhttp)
    implementation(Android.Network.retrofit)

    androidTestImplementation(Android.Test.espresso_core)
    androidTestImplementation(Android.Test.runner)
    androidTestImplementation(Android.Test.rules)
    testImplementation(Android.Test.junit)
}