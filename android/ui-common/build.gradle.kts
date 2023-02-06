plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Android.Version.composeCompiler
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))

    // Dependency Injection
    implementation(Android.Inject.hilt)
    kapt(Android.Inject.hiltCompiler)

    // UI
    api(Android.Image.coil)
    api(Android.Image.ratingbar)

    // Jetpack
    api(Android.Jetpack.appcompat)
    api(Android.Jetpack.compose)
    api(Android.Jetpack.composeTooling)
    api(Android.Jetpack.core)
    api(Android.Jetpack.navigationCompose)
    api(Android.Jetpack.navigationFragment)

    // Logging
    implementation(Android.Logging.timber)

    // Testing
    testImplementation(Android.Test.junit)
    androidTestImplementation(Android.Test.junit_ext)
    androidTestImplementation(Android.Test.espresso_core)
    androidTestImplementation(Android.Test.runner)
    androidTestImplementation(Android.Test.rules)
}