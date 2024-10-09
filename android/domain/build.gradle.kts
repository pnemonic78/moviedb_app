plugins {
    aliasId(libs.plugins.androidLibrary)
    aliasId(libs.plugins.hilt)
    aliasId(libs.plugins.kotlinAndroid)
    aliasId(libs.plugins.ksp)
}

android {
    namespace = "com.tikalk.tmdb.domain"
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

dependencies {
    api(projects.model)

    implementation(libs.di.hilt)
    ksp(libs.di.hilt.compiler)

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
    ksp(libs.db.room.compiler)

    // Testing
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitExt)
    androidTestImplementation(libs.test.espresso)
}