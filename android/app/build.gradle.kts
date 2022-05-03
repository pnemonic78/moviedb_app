plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.10"
}

android {
    compileSdk = Android.Version.compileSdk
    defaultConfig {
        applicationId = "com.tikalk.tmdb.app"
        minSdk = Android.Version.minSdk
        targetSdk = Android.Version.targetSdk
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "MDB_API_KEY", "\"${project.properties["MDB_API_KEY"]}\"")
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            // proguardFiles (getDefaultProguardFile ("proguard-android.txt"), "proguard-rules.pro")
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
    implementation(project(":model"))

    // DI
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    // Images
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("io.coil-kt:coil-compose:1.3.2")
    implementation("io.github.a914-gowtham:compose-ratingbar:1.2.2")

    // Jetpack
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.compose.material:material:${Android.Version.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Android.Version.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Android.Version.compose}")
    implementation("androidx.compose.ui:ui:${Android.Version.compose}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation(Android.Dependency.core)
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")

    // JSON
    implementation(Kotlin.Dependency.json)
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Network
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Test
    androidTestImplementation(Android.Dependency.espresso_core)
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    testImplementation(Android.Dependency.junit)
}
