plugins {
    aliasId(libs.plugins.androidLibrary)
    aliasId(libs.plugins.kotlinAndroid)
    aliasId(libs.plugins.kapt)
}

android {
    namespace = "com.tikal.tmdb.domain"
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        minSdk = Android.Version.minSdk

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "TMDB_API_KEY", "\"${project.properties["TMDB_API_KEY"]}\"")
    }
    buildFeatures {
        buildConfig = true
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

kapt {
    correctErrorTypes = true
}

dependencies {
    api(projects.model)

    implementation(libs.di.hilt)
    kapt(libs.di.hilt.compiler)

    implementation(libs.jetpack.core)

    implementation(libs.serialization.json)
    implementation(libs.net.retrofit.json)

    implementation(libs.logging.timber)

    // Networking
    implementation(libs.jetpack.paging)
    implementation(libs.net.okhttp)
    implementation(libs.net.okhttp.logging)
    implementation(libs.net.retrofit)

    // Database
    implementation(libs.db.room.kotlin)
    kapt(libs.db.room.compiler)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitExt)
    androidTestImplementation(libs.test.espresso)
}