plugins {
    aliasId(libs.plugins.androidLibrary)
    aliasId(libs.plugins.hilt)
    aliasId(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    aliasId(libs.plugins.kapt)
}

android {
    namespace = "com.tikalk.tmdb.ui.common"
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.domain)

    implementation(libs.di.hilt)
    kapt(libs.di.hilt.compiler)

    api(libs.bundles.compose)
    api(libs.compose.coil)
    api(libs.compose.ratingbar)

    api(libs.jetpack.appcompat)
    api(libs.jetpack.navigationFragment)
    api(libs.jetpack.paging)

    implementation(libs.logging.timber)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitExt)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
}