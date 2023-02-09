@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    aliasWV(androidCatalog.plugins.lib)
    aliasWV(kotlinCatalog.plugins.android)
    aliasWV(kotlinCatalog.plugins.kapt)
    alias(kotlinCatalog.plugins.serialization)
}

android {
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
    implementation(androidCatalog.di.hilt)
    kapt(androidCatalog.di.hiltCompiler)

    implementation(kotlinCatalog.json.serialization)

    implementation(androidCatalog.log.timber)

    // Networking
    implementation(androidCatalog.net.logging)
    implementation(androidCatalog.net.okhttp)
    implementation(androidCatalog.net.retrofit)

    // Testing
    testImplementation(androidCatalog.test.junit)
    androidTestImplementation(androidCatalog.test.junitExt)
    androidTestImplementation(androidCatalog.test.espresso)
}