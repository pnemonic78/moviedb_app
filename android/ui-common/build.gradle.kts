@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    aliasWV(androidCatalog.plugins.lib)
    aliasWV(androidCatalog.plugins.hilt)
    aliasWV(kotlinCatalog.plugins.android)
    aliasWV(kotlinCatalog.plugins.kapt)
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = androidCatalog.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":model"))

    implementation(androidCatalog.di.hilt)
    kapt(androidCatalog.di.hiltCompiler)

    api(androidCatalog.image.coil)
    api(androidCatalog.image.ratingbar)

    api(androidCatalog.jetpack.appcompat)
    api(androidCatalog.jetpack.compose)
    api(androidCatalog.jetpack.composeTooling)
    api(androidCatalog.jetpack.core)
    api(androidCatalog.jetpack.navigationCompose)
    api(androidCatalog.jetpack.navigationFragment)

    implementation(androidCatalog.log.timber)

    // Testing
    testImplementation(androidCatalog.test.junit)
    androidTestImplementation(androidCatalog.test.junitExt)
    androidTestImplementation(androidCatalog.test.espresso)
    androidTestImplementation(androidCatalog.test.runner)
    androidTestImplementation(androidCatalog.test.rules)
}