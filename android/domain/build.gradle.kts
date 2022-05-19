plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        minSdk = Android.Version.minSdk
        targetSdk = Android.Version.targetSdk

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "TMDB_API_KEY", "\"${project.properties["TMDB_API_KEY"]}\"")
    }

    buildTypes {
        release {
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
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":model"))

    implementation(Android.Inject.hilt)
    kapt(Android.Inject.hiltCompiler)

    implementation(Android.JSON.kotlin)
    implementation(Android.JSON.retrofit)

    implementation(Android.Network.logging)
    implementation(Android.Network.okhttp)
    implementation(Android.Network.retrofit)

    testImplementation(Android.Test.junit)
    androidTestImplementation(Android.Test.junit_ext)
    androidTestImplementation(Android.Test.espresso_core)
}