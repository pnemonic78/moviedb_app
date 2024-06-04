@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    aliasNV(androidCatalog.plugins.lib)
    aliasNV(kotlinCatalog.plugins.android)
    alias(kotlinCatalog.plugins.compose.compiler)
}

android {
    namespace = "com.tikal.tmdb.now_playing"
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
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":ui-common"))

    implementation(androidCatalog.log.timber)

    // Testing
    testImplementation(androidCatalog.test.junit)
    androidTestImplementation(androidCatalog.test.espresso)
    androidTestImplementation(androidCatalog.test.runner)
    androidTestImplementation(androidCatalog.test.rules)
}