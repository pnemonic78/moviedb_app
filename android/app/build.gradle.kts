plugins {
    aliasId(libs.plugins.androidApplication)
    aliasId(libs.plugins.hilt)
    aliasId(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    aliasId(libs.plugins.kapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.tikal.tmdb"
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        applicationId = "com.tikalk.tmdb.app"
        minSdk = Android.Version.minSdk
        targetSdk = Android.Version.targetSdk
        versionCode = 2
        versionName = "1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
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
    buildFeatures {
        compose = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(projects.domain)
    implementation(projects.model)
    implementation(projects.uiCommon)
    implementation(projects.featureCast)
    implementation(projects.featureNowPlaying)
    implementation(projects.featurePopular)
    implementation(projects.featureTopRated)
    implementation(projects.featureUpcoming)

    implementation(libs.di.hilt)
    kapt(libs.di.hilt.compiler)

    implementation(libs.logging.timber)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
}