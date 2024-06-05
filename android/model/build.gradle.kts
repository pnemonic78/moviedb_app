plugins {
    aliasNV(libs.plugins.androidLibrary)
    aliasNV(libs.plugins.kotlinAndroid)
    aliasNV(libs.plugins.kapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.tikal.tmdb.model"
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        minSdk = Android.Version.minSdk

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
}

dependencies {
    implementation(libs.di.hilt)
    kapt(libs.di.hilt.compiler)

    implementation(libs.serialization.json)

    implementation(libs.logging.timber)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitExt)
    androidTestImplementation(libs.test.espresso)
}