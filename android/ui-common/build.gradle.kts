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
        getByName("release") {
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Android.Version.compose
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))

    implementation(Android.Inject.hilt)
    kapt(Android.Inject.hiltCompiler)

    api(Android.Image.coil)
    api(Android.Image.ratingbar)

    api(Android.Jetpack.appcompat)
    api(Android.Jetpack.compose)
    api(Android.Jetpack.composeTooling)
    api(Android.Jetpack.core)
    api(Android.Jetpack.navigationCompose)
    api(Android.Jetpack.navigationFragment)
    api(Android.Jetpack.navigationUI)

    implementation(Android.Logging.timber)

    testImplementation(Android.Test.junit)
    androidTestImplementation(Android.Test.junit_ext)
    androidTestImplementation(Android.Test.espresso_core)
    androidTestImplementation(Android.Test.runner)
    androidTestImplementation(Android.Test.rules)
}