@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    aliasWV(androidCatalog.plugins.lib)
    aliasWV(kotlinCatalog.plugins.android)
    aliasWV(kotlinCatalog.plugins.kapt)
}

android {
    compileSdk = Android.Version.compileSdk

    defaultConfig {
        minSdk = Android.Version.minSdk

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":model"))

    implementation(androidCatalog.di.hilt)
    kapt(androidCatalog.di.hiltCompiler)

    implementation(androidCatalog.jetpack.core)

    implementation(kotlinCatalog.json.serialization)
    implementation(androidCatalog.json.retrofit)

    implementation(androidCatalog.log.timber)

    // Networking
    implementation(androidCatalog.jetpack.paging)
    implementation(androidCatalog.net.logging)
    implementation(androidCatalog.net.okhttp)
    implementation(androidCatalog.net.retrofit)

    // Database
    implementation(androidCatalog.db.roomKtx)
    kapt(androidCatalog.db.roomCompiler)

    // Testing
    testImplementation(androidCatalog.test.junit)
    androidTestImplementation(androidCatalog.test.junitExt)
    androidTestImplementation(androidCatalog.test.espresso)
}