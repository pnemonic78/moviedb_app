@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    aliasWV(androidCatalog.plugins.app)
    aliasWV(androidCatalog.plugins.hilt)
    aliasWV(kotlinCatalog.plugins.android)
    aliasWV(kotlinCatalog.plugins.kapt)
    alias(kotlinCatalog.plugins.serialization)
}

android {
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = androidCatalog.versions.composeCompiler.get()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":ui-common"))
    implementation(project(":feature-now-playing"))

    implementation(androidCatalog.di.hilt)
    kapt(androidCatalog.di.hiltCompiler)

    implementation(androidCatalog.log.timber)

    // Testing
    testImplementation(androidCatalog.test.junit)
    androidTestImplementation(androidCatalog.test.espresso)
    androidTestImplementation(androidCatalog.test.runner)
    androidTestImplementation(androidCatalog.test.rules)
}