plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization") version "1.6.10"
}

android {
    compileSdk = Depends.compileSdk

    defaultConfig {
        minSdk = Depends.minSdk
        targetSdk = Depends.targetSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

dependencies {
    // Jetpack
    implementation("androidx.core:core-ktx:1.7.0")

    // JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}