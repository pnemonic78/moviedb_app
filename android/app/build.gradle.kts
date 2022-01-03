plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = rootProject.extra["compileSdkVersion"] as Int
    defaultConfig {
        applicationId = "com.tikalk.tmdb.app"
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["targetSdkVersion"] as Int
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Jetpack
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlinVersion"]}")

    // Rx
    implementation("io.reactivex.rxjava2:rxandroid:${rootProject.extra["rxandroid2Version"]}")
    implementation("io.reactivex.rxjava2:rxkotlin:${rootProject.extra["rxkotlin2Version"]}")
    implementation("com.jakewharton.rxbinding2:rxbinding-kotlin:${rootProject.extra["rxkbinding2Version"]}")

    // DI
    implementation("com.google.dagger:dagger:${rootProject.extra["dagger2Version"]}")
    implementation("com.google.dagger:dagger-android:${rootProject.extra["dagger2Version"]}")
    implementation("com.google.dagger:dagger-android-support:${rootProject.extra["dagger2Version"]}")
    kapt("com.google.dagger:dagger-android-processor:${rootProject.extra["dagger2Version"]}")
    kapt("com.google.dagger:dagger-compiler:${rootProject.extra["dagger2Version"]}")

    // SQL
    implementation("com.squareup.sqlbrite2:sqlbrite-kotlin:${rootProject.extra["sqlbrite2Version"]}")

    // Logging
    implementation("com.jakewharton.timber:timber:${rootProject.extra["timberVersion"]}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit2Version"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit2Version"]}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${rootProject.extra["retrofit2Version"]}")
    implementation("com.squareup.okhttp3:okhttp:${rootProject.extra["okhttp3Version"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["okhttp3Version"]}")

    // Images
    implementation("com.github.bumptech.glide:glide:${rootProject.extra["glideVersion"]}")

    // Test
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["espressoVersion"]}")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    testImplementation("junit:junit:${rootProject.extra["junitVersion"]}")
}
