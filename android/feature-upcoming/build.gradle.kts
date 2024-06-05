plugins {
    aliasNV(libs.plugins.androidLibrary)
    aliasNV(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.tikal.tmdb.upcoming"
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.domain)
    implementation(projects.uiCommon)

    implementation(libs.logging.timber)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.test.rules)
}