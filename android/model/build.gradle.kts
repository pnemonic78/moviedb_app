plugins {
    aliasId(libs.plugins.androidLibrary)
    aliasId(libs.plugins.kotlinAndroid)
    aliasId(libs.plugins.kapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.tikalk.tmdb.model"
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
        sourceCompatibility = Java.Version.jvm
        targetCompatibility = Java.Version.jvm
    }
    kotlinOptions {
        jvmTarget = Java.Version.jvm.toString()
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